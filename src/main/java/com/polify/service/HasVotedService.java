package com.polify.service;

import com.polify.entity.HasVoted;

public interface HasVotedService {

    public Boolean getHasVoted(Long pollId, Long userId);
    public HasVoted addHasVoted(Long pollId, Long userId, Long pollOptionId);
    public Long votedOn(Long pollId, Long userId);
}
