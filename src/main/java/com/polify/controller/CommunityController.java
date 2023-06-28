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
import java.util.Map;

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
    public Map<String, Object> addCommunity(@RequestBody CommunityDTO communityDTO,
                            Authentication authentication){
        String username = authentication.getName();
        User creator = userAccountService.getUserByUsername(username);

        Community community = new Community();
        community.setCommunityName(communityDTO.getCommunityName());
        community.setCommunityDescription(communityDTO.getCommunityDescription());
        community.setUsers(creator);
        Community savedCommunity = communityService.addCommunity(community);

        List<Long> userIdList = communityDTO.getUserId();
        userIdList.add(0, creator.getId());
        for (Long userId: userIdList) {
            CommunityMembers communityMembers = new CommunityMembers();
            communityMembers.setCommunity(savedCommunity);
            User user = communityService.getUserById(userId);
            communityMembers.setUsers(user);
            communityMembersService.addCommunityMembers(communityMembers);
        }

        return communityMembersService.getCommunityMembersResponse(savedCommunity.getId());
    }
}
