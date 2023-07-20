package com.polify.service.impl;

import com.polify.entity.Community;
import com.polify.entity.Poll;
import com.polify.entity.User;
import com.polify.repository.CommunityRepository;
import com.polify.repository.PollRepository;
import com.polify.repository.UserRepository;
import com.polify.service.HasVotedService;
import com.polify.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PollServiceImpl implements PollService {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HasVotedService hasVotedService;

    @Override
    public Poll getPoll(Long id) {
        return pollRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Poll not found!!!"));
    }

    @Override
    public List<Poll> getCommunityPoll(UUID id) {
        return pollRepository.findByCommunityId(id);
    }

    @Override
    public Poll addPoll(Poll poll) {
        return pollRepository.save(poll);
    }

    @Override
    public Community getCommunityById(UUID id) {
        return communityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Community not found!!!"));
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!!!"));
    }

    @Override
    public Map<String, Object> getPollResponse(Poll poll, User user) {
        Map<String, Object> pollMap = new HashMap<>();
        pollMap.put("id", poll.getId());
        pollMap.put("pollQuestion", poll.getPollQuestion());
        pollMap.put("limitVote", poll.getLimitVote());
        pollMap.put("duration", poll.getDuration());
        pollMap.put("pollDate", poll.getPollDate());
        pollMap.put("user", poll.getUser());
        pollMap.put("hasVoted", hasVotedService.getHasVoted(poll.getId(), user.getId()));
        pollMap.put("votedOn", hasVotedService.votedOn(poll.getId(), user.getId()));
        pollMap.put("totalVote", poll.getTotalVote());
        return pollMap;
    }

    @Override
    public void updateTotalVote(Long id, Long totalVote) {
        Poll poll = pollRepository.findById(id).orElseThrow( ()-> new RuntimeException("Poll not found!!!"));
        poll.setTotalVote(totalVote);
        pollRepository.save(poll);
    }


}
