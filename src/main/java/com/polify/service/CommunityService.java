package com.polify.service;

import com.polify.entity.Community;
import com.polify.entity.User;

import java.util.List;
import java.util.UUID;

public interface CommunityService {

    public List<Community> getAllCommunity();

    public Community addCommunity(Community community);

    public User getUserById(Long id);

    public List<Community> getCommunityByUserId(Long user_id);

    public Community getCommunityById(UUID id);

}
