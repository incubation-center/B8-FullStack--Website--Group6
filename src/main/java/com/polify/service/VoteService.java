package com.polify.service;

import com.polify.entity.PollOption;
import com.polify.entity.User;
import com.polify.entity.Vote;

import java.util.List;

public interface VoteService {

    public List<Vote> getVote();

    public void addVote(Vote vote);

    public User getUserById(Long id);

    public PollOption getPollOptionById(Long id);
}
