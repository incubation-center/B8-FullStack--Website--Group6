package com.polify.service.impl;

import com.polify.entity.PollOption;
import com.polify.entity.User;
import com.polify.entity.Vote;
import com.polify.repository.PollOptionRepository;
import com.polify.repository.UserRepository;
import com.polify.repository.VoteRepository;
import com.polify.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PollOptionRepository pollOptionRepository;

    @Override
    public List<Vote> getVote() {
        return voteRepository.findAll();
    }

    @Override
    public void addVote(Vote vote) {
        voteRepository.save(vote);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!!!"));
    }

    @Override
    public PollOption getPollOptionById(Long id) {
        return pollOptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Poll Option not found!!!"));
    }
}
