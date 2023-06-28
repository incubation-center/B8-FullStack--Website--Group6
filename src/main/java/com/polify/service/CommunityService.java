package com.polify.service;

import com.polify.entity.Community;
import com.polify.entity.User;

import java.util.List;

public interface CommunityService {

    public List<Community> getAllCommunity();

    public Community addCommunity(Community community);

    public User getUserById(Long id);

    public List<Community> getCommunity(Long user_id);

}
