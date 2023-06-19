package com.polify.controller;

import com.polify.entity.CommunityMembers;
import com.polify.service.CommunityMembersService;
import com.polify.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ProjectUtils.COMMUNITY_MEMBERS_URL)
public class CommunityMembersController {

    @Autowired
    private CommunityMembersService communityMembersService;

    @GetMapping(path = "{id}")
    public List<CommunityMembers> getCommunityMembers(@PathVariable Long id){
        return communityMembersService.getCommunityMembers(id);
    }
}
