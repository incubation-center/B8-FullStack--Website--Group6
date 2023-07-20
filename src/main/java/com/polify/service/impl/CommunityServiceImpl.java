package com.polify.service.impl;

import com.polify.entity.Community;
import com.polify.entity.CommunityMembers;
import com.polify.entity.User;
import com.polify.repository.CommunityRepository;
import com.polify.repository.UserRepository;
import com.polify.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommunityServiceImpl implements CommunityService {


    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Map<String, Object>> getAllCommunity() {
        List<Community> allCommunity = communityRepository.findAll();
        List<Map<String, Object>> response = new ArrayList<>();

        for (Community community: allCommunity){
            Map<String, Object> communityMap = new HashMap<>();
            communityMap.put("id", community.getId());
            communityMap.put("name", community.getCommunityName());
            response.add(communityMap);
        }
        return response;
    }

    @Override
    public Community addCommunity(Community community) {
        return communityRepository.save(community);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!!!"));
    }

    @Override
    public List<Community> getCommunityByUserId(Long user_id) {
        return communityRepository.findByUsersId(user_id);
    }

    @Override
    public Community getCommunityById(UUID id) {
        return communityRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Community not found!!!"));
    }

    @Override
    public void deleteCommunityById(UUID id) {
        communityRepository.deleteById(id);
    }

}
