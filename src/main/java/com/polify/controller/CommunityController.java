package com.polify.controller;

import com.polify.entity.Community;
import com.polify.entity.CommunityMembers;
import com.polify.entity.User;
import com.polify.model.CommunityDTO;
import com.polify.service.CommunityMembersService;
import com.polify.service.CommunityService;
import com.polify.service.UserAccountService;
import com.polify.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ProjectUtils.COMMUNITY_URL)
public class CommunityController {

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

    @GetMapping(path = "{user_id}")
    public List<Community> getCommunity(@PathVariable Long user_id){
        return communityService.getCommunity(user_id);
    }

    @PostMapping(path = "")
    public ResponseEntity<CommunityMembers> addCommunity(@RequestBody CommunityDTO communityDTO, Authentication authentication){
        Community community = new Community();
        community.setCommunityName(communityDTO.getCommunityName());
        community.setCommunityDescription(communityDTO.getCommunityDescription());

        String email = authentication.getName();
        User user = userAccountService.getUserByEmail(email);

        community.setUsers(communityService.getUserById(user.getId()));
        Long community_id = communityService.addCommunity(community);

        CommunityMembers communityMembers = new CommunityMembers();
        communityMembers.setCommunity(communityMembersService.getCommunityById(community_id));
        communityMembers.setUsers(communityMembersService.getUserById(user.getId()));

        CommunityMembers savedCommunityMembers = communityMembersService.addCommunityMembers(communityMembers);
        return ResponseEntity.ok(savedCommunityMembers);
    }
}
