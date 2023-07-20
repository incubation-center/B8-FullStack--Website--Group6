package com.polify.service;

import com.polify.entity.Community;
import com.polify.entity.Poll;
import com.polify.entity.User;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface PollService {

    public Poll getPoll(Long id);

    public List<Poll> getCommunityPoll(UUID id);

    public Poll addPoll(Poll poll);

    public Community getCommunityById(UUID id);

    public User getUserById(Long id);

    public Map<String, Object> getPollResponse(Poll poll, User user);

    public void updateTotalVote(Long id, Long totalVote);
}
