package com.polify.controller;

import com.polify.entity.Community;
import com.polify.entity.CommunityMembers;
import com.polify.entity.User;
import com.polify.service.CommunityMembersService;
import com.polify.service.UserAccountService;
import com.polify.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(ProjectUtils.COMMUNITY_MEMBERS_URL)
public class CommunityMembersController {

    @Autowired
    private CommunityMembersService communityMembersService;

    @Autowired
    private UserAccountService userAccountService;

    @GetMapping(path = "/user")
    public Map<String, Object> getUserCommunity(Authentication authentication){
        String username = authentication.getName();
        User user = userAccountService.getUserByUsername(username);

        return communityMembersService.getUserCommunityResponse(user.getId());
    }

    @GetMapping(path = "/community/{id}")
    public Map<String, Object> getCommunityMembers(@PathVariable UUID id){
        return communityMembersService.getCommunityMembersResponse(id);
    }

    @GetMapping(path = "/join/community/{id}")
    public Map<String, Object> joinMember(@PathVariable UUID id, Authentication authentication){

        String username = authentication.getName();
        User user = userAccountService.getUserByUsername(username);

        Community community = communityMembersService.getCommunityById(id);

        CommunityMembers communityMembers = communityMembersService.isExist(community, user);
        Map<String, Object> joinCommunity = new HashMap<>();
        Boolean isMember;
        if (communityMembers != null){
            isMember = Boolean.TRUE;
        }
        else{
            isMember = Boolean.FALSE;
        }
        joinCommunity.put("isMember", isMember);
        return joinCommunity;
    }

    @PostMapping(path = "/community/{id}")
    public ResponseEntity<Object> addCommunityMembers(@PathVariable UUID id, Authentication authentication){

        String username = authentication.getName();
        User user = userAccountService.getUserByUsername(username);

        Community community = communityMembersService.getCommunityById(id);

        if (communityMembersService.isExist(community, user) == null){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("User is already in Community!!!");
        }

        CommunityMembers savedCommunityMembers = communityMembersService.addCommunityMembers(community, user, "poller");

        return ResponseEntity.ok(savedCommunityMembers);
    }

    @CrossOrigin(origins = "https://newbootcamp.vercel.app")
    @PutMapping(path = "/community/{id}")
    public ResponseEntity<Map<String, Object>> updateMemberRole(@RequestBody Map<String, List<Map<String, Object>>> body, @PathVariable UUID id){
        List<Map<String, Object>> roleList = body.get("roleList");
        System.out.println("Role List " + roleList);

        Community community = communityMembersService.getCommunityById(id);

        for (Map<String, Object> roleObject: roleList){
            Long userId = ((Number)roleObject.get("id")).longValue();
            User user = communityMembersService.getUserById(userId);
            CommunityMembers communityMembers = communityMembersService.isExist(community, user);
            if (communityMembers == null){
                Map<String, Object> errorMessage = new HashMap<>();
                errorMessage.put("Message", "User Id " + " is not in this community!!!");
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorMessage);
            }
            communityMembers.setRole((String) roleObject.get("role"));
            communityMembersService.saveCommunityMember(communityMembers);
        }

        return ResponseEntity.ok(communityMembersService.getCommunityMembersResponse(id));
    }
}