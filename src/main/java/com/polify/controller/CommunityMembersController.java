package com.polify.controller;

import com.polify.entity.Community;
import com.polify.entity.CommunityMembers;
import com.polify.entity.User;
import com.polify.model.CommunityMembersDTO;
import com.polify.service.CommunityMembersService;
import com.polify.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ProjectUtils.COMMUNITY_MEMBERS_URL)
public class CommunityMembersController {

    @Autowired
    private CommunityMembersService communityMembersService;

    @GetMapping(path = "/user/{id}")
    public List<CommunityMembers> getUserCommunity(@PathVariable Long id){
        return communityMembersService.getUserCommunity(id);
    }

    @GetMapping(path = "/community/{id}")
    public List<CommunityMembers> getCommunityMembers(@PathVariable Long id){
        return communityMembersService.getCommunityMembers(id);
    }

    @PostMapping
    public ResponseEntity<CommunityMembers> addCommunityMembers(@RequestBody CommunityMembersDTO communityMembersDTO){

        User user = communityMembersService.getUserById(communityMembersDTO.getUser_id());
        Community community = communityMembersService.getCommunityById(communityMembersDTO.getCommunity_id());

        if (communityMembersService.isExist(community, user)){
            throw new IllegalStateException("User is already in Community!!!");
        }
        CommunityMembers communityMembers = new CommunityMembers();
        communityMembers.setUsers(user);
        communityMembers.setCommunity(community);

        CommunityMembers savedCommunityMembers = communityMembersService.addCommunityMembers(communityMembers);

        return ResponseEntity.ok(savedCommunityMembers);
    }
}
