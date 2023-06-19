package com.polify.service;

import com.polify.entity.CommunityMembers;

import java.util.List;

public interface CommunityMembersService {

    public List<CommunityMembers> getCommunityMembers(Long id);

    public void addCommunityMembers();
}
