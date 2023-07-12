package com.polify.service.impl;

import com.polify.entity.Community;
import com.polify.entity.CommunityMembers;
import com.polify.entity.User;
import com.polify.repository.CommunityMembersRepository;
import com.polify.repository.CommunityRepository;
import com.polify.repository.UserRepository;
import com.polify.service.CommunityMembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class CommunityMembersServiceImpl implements CommunityMembersService {

    @Autowired
    private CommunityMembersRepository communityMembersRepository;

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<CommunityMembers> getCommunityMembers(Long id) {
        return communityMembersRepository.findByCommunityId(id);
    }

    @Override
    public List<CommunityMembers> getUserCommunity(Long id) {
        return communityMembersRepository.findByUsersId(id);
    }

    @Override
    public CommunityMembers addCommunityMembers(CommunityMembers communityMembers) {
        return communityMembersRepository.save(communityMembers);
    }

    @Override
    public User getUserById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found!!!"));
    }

    @Override
    public Community getCommunityById(Long communityId){
        return communityRepository.findById(communityId)
                .orElseThrow(() -> new RuntimeException("Community not found!!!"));
    }

    @Override
    public boolean isExist(Community community, User user) {
        return communityMembersRepository.findByCommunityAndUsers(community, user).isPresent();
    }

    @Override
    public Map<String, Object> getCommunityMembersResponse(Long id) {
        Map<String, Object> response = new HashMap<>();

        List<CommunityMembers> communityMembers = getCommunityMembers(id);
        Community community = communityRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Community not found!!!"));

        String name = community.getCommunityName();
        LocalDate dateCreated = community.getDateCreated();

        response.put("id", id);
        response.put("name", name);
        response.put("dateCreated", dateCreated);

        List<Map<String, Object>> userList = new ArrayList<>();
        for (CommunityMembers communityMember: communityMembers) {
            Map<String, Object> userMap = new HashMap<>();
            User user = communityMember.getUsers();

            userMap.put("id", user.getId());
            userMap.put("username", user.getUsername());
            userMap.put("email", user.getEmail());

            userList.add(userMap);
        }

        response.put("user", userList);


        return response;
    }

    @Override
    public Map<String, Object> getUserCommunityResponse(Long id) {
        Map<String, Object> response = new HashMap<>();

        List<CommunityMembers> communityMembers = getUserCommunity(id);
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found!!!"));

        response.put("id", id);
        response.put("username", user.getUsername());
        response.put("email", user.getEmail());

        List<Map<String, Object>> communityList = new ArrayList<>();
        for (CommunityMembers communityMember: communityMembers) {
            Map<String, Object> communityMap = new HashMap<>();
            Community community = communityMember.getCommunity();
            communityMap.put("id", community.getId());
            communityMap.put("name", community.getCommunityName());

            communityList.add(communityMap);
        }
        response.put("community", communityList);

        return response;
    }
}
