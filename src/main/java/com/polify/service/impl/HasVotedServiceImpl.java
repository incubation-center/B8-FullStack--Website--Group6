package com.polify.service.impl;

import com.polify.entity.HasVoted;
import com.polify.entity.Poll;
import com.polify.entity.PollOption;
import com.polify.entity.User;
import com.polify.repository.HasVotedRepository;
import com.polify.repository.PollOptionRepository;
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

    @Autowired
    private PollOptionRepository pollOptionRepository;

    @Override
    public Boolean getHasVoted(Long pollId, Long userId) {

        Poll poll = pollRepository.findById(pollId)
            .orElseThrow(() -> new RuntimeException("Poll not found!!!"));

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found!!!"));

        if (hasVotedRepository.findByPollAndUser(poll, user) != null){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public HasVoted addHasVoted(Long pollId, Long userId, Long pollOptionId) {
        Poll poll = pollRepository.findById(pollId)
            .orElseThrow(() -> new RuntimeException("Poll not found!!!"));

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found!!!"));

        PollOption pollOption = pollOptionRepository.findById(pollOptionId)
            .orElseThrow(() -> new RuntimeException("Poll Option not found!!!"));

        HasVoted hasVoted = new HasVoted();
        hasVoted.setPoll(poll);
        hasVoted.setUser(user);
        hasVoted.setPollOption(pollOption);

        return hasVotedRepository.save(hasVoted);
    }

    @Override
    public Long votedOn(Long pollId, Long userId) {

        Poll poll = pollRepository.findById(pollId)
            .orElseThrow(() -> new RuntimeException("Poll not found!!!"));

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found!!!"));

        HasVoted hasVoted = hasVotedRepository.findByPollAndUser(poll, user);
        if (hasVoted != null){
            return hasVoted.getId();
        }
        return null;
    }
}
