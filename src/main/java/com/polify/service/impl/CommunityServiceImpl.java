package com.polify.service.impl;

import com.polify.entity.Community;
import com.polify.entity.User;
import com.polify.repository.CommunityMembersRepository;
import com.polify.repository.CommunityRepository;
import com.polify.repository.UserRepository;
import com.polify.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService {


    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Community> getAllCommunity() {
        return communityRepository.findAll();
    }

    @Override
    public Long addCommunity(Community community) {
        Community savedCommunity = communityRepository.save(community);
        return savedCommunity.getId();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!!!"));
    }

    @Override
    public List<Community> getCommunity(Long user_id) {
        return communityRepository.findByUsersId(user_id);
    }

}
