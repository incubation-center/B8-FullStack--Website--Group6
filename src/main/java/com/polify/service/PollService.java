package com.polify.service;

import com.polify.entity.Poll;
import com.polify.entity.Post;

import java.util.List;

public interface PollService {


    public List<Poll> getPoll();

    public void addPoll(Poll poll);

    public Post getPostById(Long id);
}
