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

import java.util.List;
import java.util.Optional;

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
}
