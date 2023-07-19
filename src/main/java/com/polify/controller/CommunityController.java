package com.polify.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.polify.entity.Community;
import com.polify.entity.CommunityMembers;
import com.polify.entity.User;
import com.polify.filter.JWTAuthenticationFilter;
import com.polify.model.CommunityDTO;
import com.polify.service.CommunityMembersService;
import com.polify.service.CommunityService;
import com.polify.service.UserAccountService;
import com.polify.utils.ProjectUtils;
import com.polify.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.Console;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping(ProjectUtils.COMMUNITY_URL)
public class CommunityController {

    public static final String SECRET = "FG^723fhhQW12~123qwert123#@$fr!67DSWa";
    public static final long EXPIRATION_TIME = 86400000;

    @Autowired
    private CommunityService communityService;

    @Autowired
    private CommunityMembersService communityMembersService;

    @Autowired
    private UserAccountService userAccountService;

    @GetMapping(path = "/all")
    public List<Community> getAllCommunity(){
        return communityService.getAllCommunity();
    }

    @GetMapping(path = "/user/{user_id}")
    public List<Community> getCommunity(@PathVariable Long user_id){
        return communityService.getCommunityByUserId(user_id);
    }

    @PostMapping(value = "", consumes = "multipart/form-data")
    public Map<String, Object> addCommunity(CommunityDTO communityDTO, @RequestPart(name = "file", required = false) MultipartFile file) throws IOException {
        String uploadUrl = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User creator = userAccountService.getUserByUsername(username);
        String file_name = null;
        String file_url = null;
        if (file != null) {
            uploadUrl = ProjectUtils.FILE_URL;
            byte[] fileBytes = file.getBytes();
            String fileName = file.getOriginalFilename();
            file_name = Utils.uploadFile(fileBytes, fileName);
            file_url = uploadUrl + "/files/" + file_name;
        }

        Community community = new Community();
        community.setCommunityName(communityDTO.getCommunityName());
        community.setUsers(creator);
        community.setImage(file_name);
        Community savedCommunity = communityService.addCommunity(community);

        List<Long> userIdList = communityDTO.getUserId();
        communityMembersService.addCommunityMembers(community, creator, "owner");
        for (Long userId : userIdList) {
            User user = communityService.getUserById(userId);
            communityMembersService.addCommunityMembers(community, user, "poller");
        }

        Object resCommunity = communityMembersService.getCommunityMembersResponse(savedCommunity.getId());

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("community", resCommunity);
        response.put("file_url", file_url);

        return response;
    }

    @PostMapping(consumes = "multipart/form-data", path = "/{id}")
    public Map<String, Object> updateCommunity(CommunityDTO communityDTO,
                                               @RequestPart(name = "file", required = false) MultipartFile file,
                                               @PathVariable UUID id) throws IOException{
        Community community = communityService.getCommunityById(id);

        String uploadUrl = ProjectUtils.FILE_URL;
        String file_name;
        if (file != null) {
            byte[] fileBytes = file.getBytes();
            String fileName = file.getOriginalFilename();
            file_name = Utils.uploadFile(fileBytes, fileName);
        }
        else{
            file_name = community.getImage();
        }
        String file_url = uploadUrl + "/files/" + file_name;
        community.setCommunityName(communityDTO.getCommunityName());
        community.setImage(file_name);


        Community updatedCommunity = communityService.addCommunity(community);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("community", updatedCommunity);
        response.put("file_url", file_url);

        return response;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteCommunity(@PathVariable UUID id, Authentication authentication){

        String username = authentication.getName();
        User user = userAccountService.getUserByUsername(username);

        Community community = communityService.getCommunityById(id);

        CommunityMembers communityMembers = communityMembersService.isExist(community, user);

        if (communityMembers != null) {
            if (!Objects.equals(communityMembers.getRole(), "owner")){
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("User is not the Owner!!!");
            }
            communityService.deleteCommunityById(id);
            return ResponseEntity.ok("Community has been deleted!!!");
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("User is not in the Community!!!");

    }

    @PostMapping(path = "/generate")
    public ResponseEntity<Map<String, String>> generateInviteLink(@RequestParam("id") UUID id) {
        String token = JWT.create().withSubject(id.toString())
            .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // JWT token validity time
            .sign(Algorithm.HMAC512(SECRET.getBytes())); // JWT Signature

        Map<String, String> response = new HashMap<>();
        response.put("inviteLink", ProjectUtils.CLIENT_INVITE_URL + token);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/validate")
    public ResponseEntity<Map<String, Object>> validateInviteLink(@RequestParam("token") String token, Authentication authentication) {
        Map<String, Object> response = new HashMap<>();
        if (token != null) {
            try {
                String community_id = JWT.require(Algorithm.HMAC512(JWTAuthenticationFilter.SECRET.getBytes())).build()
                    .verify(token).getSubject();

                Community community = communityService.getCommunityById(UUID.fromString(community_id));

                String username = authentication.getName();
                User user = userAccountService.getUserByUsername(username);

                CommunityMembers communityMembers = communityMembersService.isExist(community, user);
                Boolean isMember;
                if (communityMembers != null){
                    isMember = Boolean.TRUE;
                }
                else{
                    isMember = Boolean.FALSE;
                }

                response.put("isMember", isMember);
                return ResponseEntity.ok(response);
            } catch (SignatureVerificationException e) {
                response.put("Error", "Authentication error, SignatureVerification fail.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            } catch (TokenExpiredException e) {
                response.put("Error", "Authentication error, The Invitation Link's Expired.");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        }
        response.put("Error", "Authentication error, No Token provided.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
