package com.polify.service.impl;

import com.polify.entity.HasVoted;
import com.polify.entity.Poll;
import com.polify.entity.User;
import com.polify.repository.HasVotedRepository;
import com.polify.repository.PollRepository;
import com.polify.repository.UserRepository;
import com.polify.service.HasVotedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HasVotedServiceImpl implements HasVotedService {

    @Autowired
    private HasVotedRepository hasVotedRepository;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean getHasVoted(Long pollId, Long userId) {

        Poll poll = pollRepository.findById(pollId)
            .orElseThrow(() -> new RuntimeException("Poll not found!!!"));

        User user = userRepository.findById(pollId)
            .orElseThrow(() -> new RuntimeException("User not found!!!"));

        return hasVotedRepository.findByPollAndUser(poll, user).isPresent();
    }

    @Override
    public HasVoted addHasVoted(Long pollId, Long userId) {
        Poll poll = pollRepository.findById(pollId)
            .orElseThrow(() -> new RuntimeException("Poll not found!!!"));

        User user = userRepository.findById(pollId)
            .orElseThrow(() -> new RuntimeException("User not found!!!"));

        HasVoted hasVoted = new HasVoted();
        hasVoted.setPoll(poll);
        hasVoted.setUser(user);

        return hasVotedRepository.save(hasVoted);
    }
}
