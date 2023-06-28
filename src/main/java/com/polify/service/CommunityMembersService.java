package com.polify.service;

import com.polify.entity.Community;
import com.polify.entity.CommunityMembers;
import com.polify.entity.User;

import java.util.List;
import java.util.Map;

public interface CommunityMembersService {

    public List<CommunityMembers> getCommunityMembers(Long id);

    public List<CommunityMembers> getUserCommunity(Long id);

    public CommunityMembers addCommunityMembers(CommunityMembers communityMembers);

    public User getUserById(Long userId);

    public Community getCommunityById(Long communityId);

    public boolean isExist(Community community, User user);

    public Map<String, Object> getCommunityMembersResponse(Long id);

    public Map<String, Object> getUserCommunityResponse(Long id);
}
