package com.polify.controller;

import com.polify.entity.Community;
import com.polify.entity.CommunityMembers;
import com.polify.model.CommunityDTO;
import com.polify.service.CommunityMembersService;
import com.polify.service.CommunityService;
import com.polify.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ProjectUtils.COMMUNITY_URL)
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @Autowired
    private CommunityMembersService communityMembersService;

    @GetMapping(path = "/all")
    public List<Community> getAllCommunity(){
        return communityService.getAllCommunity();
    }

    @GetMapping(path = "{user_id}")
    public List<Community> getCommunity(@PathVariable Long user_id){
        return communityService.getCommunity(user_id);
    }

    @PostMapping(path = "{user_id}")
    public void addCommunity(@RequestBody CommunityDTO communityDTO, @PathVariable Long user_id){
        Community community = new Community();
        community.setCommunityName(communityDTO.getCommunityName());
        community.setCommunityDescription(communityDTO.getCommunityDescription());
        community.setUsers(communityService.getUserById(user_id));
        Long community_id = communityService.addCommunity(community);

        CommunityMembers communityMembers = new CommunityMembers();
        communityMembers.setCommunity(communityMembersService.getCommunityById(community_id));
        communityMembers.setUsers(communityMembersService.getUserById(user_id));

        communityMembersService.addCommunityMembers(communityMembers);
    }
}
