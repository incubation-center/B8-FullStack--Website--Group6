package com.polify.service;

import com.polify.entity.Community;
import com.polify.entity.CommunityMembers;
import com.polify.entity.User;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface CommunityMembersService {

    public List<CommunityMembers> getCommunityMembers(UUID id);

    public List<CommunityMembers> getUserCommunity(Long id);

    public CommunityMembers addCommunityMembers(Community community, User user, String role);

    public User getUserById(Long userId);

    public Community getCommunityById(UUID communityId);

    public CommunityMembers isExist(Community community, User user);

    public Map<String, Object> getCommunityMembersResponse(UUID id);

    public Map<String, Object> getUserCommunityResponse(Long id);

    public CommunityMembers saveCommunityMember(CommunityMembers communityMembers);
}
