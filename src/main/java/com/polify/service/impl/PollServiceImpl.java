package com.polify.service.impl;

import com.polify.entity.Poll;
import com.polify.entity.Post;
import com.polify.repository.PollRepository;
import com.polify.repository.PostRepository;
import com.polify.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollServiceImpl implements PollService {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Poll> getPoll() {
        return pollRepository.findAll();
    }

    @Override
    public void addPoll(Poll poll) {
        pollRepository.save(poll);
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found!!!"));
    }
}
