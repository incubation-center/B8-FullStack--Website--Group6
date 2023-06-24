package com.polify.controller;

import com.polify.entity.Community;
import com.polify.entity.CommunityMembers;
import com.polify.entity.User;
import com.polify.model.CommunityMembersDTO;
import com.polify.service.CommunityMembersService;
import com.polify.service.UserAccountService;
import com.polify.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(ProjectUtils.COMMUNITY_MEMBERS_URL)
public class CommunityMembersController {

    @Autowired
    private CommunityMembersService communityMembersService;

    @Autowired
    private UserAccountService userAccountService;

    @GetMapping(path = "/user")
    public List<CommunityMembers> getUserCommunity(Authentication authentication){
        String username = authentication.getName();
        User user = userAccountService.getUserByUsername(username);

        return communityMembersService.getUserCommunity(user.getId());
    }

    @GetMapping(path = "/community/{id}")
    public List<CommunityMembers> getCommunityMembers(@PathVariable Long id){
        return communityMembersService.getCommunityMembers(id);
    }

    @PostMapping
    public ResponseEntity<Object> addCommunityMembers(@RequestBody CommunityMembersDTO communityMembersDTO){

        User user = communityMembersService.getUserById(communityMembersDTO.getUser_id());
        Community community = communityMembersService.getCommunityById(communityMembersDTO.getCommunity_id());
        if (communityMembersService.isExist(community, user)){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("User is already in Community!!!");
        }
        CommunityMembers communityMembers = new CommunityMembers();
        communityMembers.setUsers(user);
        communityMembers.setCommunity(community);

        CommunityMembers savedCommunityMembers = communityMembersService.addCommunityMembers(communityMembers);

        return ResponseEntity.ok(savedCommunityMembers);
    }
}
