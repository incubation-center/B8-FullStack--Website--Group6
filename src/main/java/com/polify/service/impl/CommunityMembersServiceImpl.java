package com.polify.service.impl;

import com.polify.entity.CommunityMembers;
import com.polify.repository.CommunityMembersRepository;
import com.polify.service.CommunityMembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityMembersServiceImpl implements CommunityMembersService {

    @Autowired
    private CommunityMembersRepository communityMembersRepository;

    @Override
    public List<CommunityMembers> getCommunityMembers(Long id) {
        return communityMembersRepository.findByCommunity(id);
    }

    @Override
    public void addCommunityMembers() {

    }
}
