package com.polify.service;

import com.polify.entity.Poll;
import com.polify.entity.PollOption;
import com.polify.entity.User;

import java.util.List;
import java.util.Map;

public interface PollOptionService {

    public List<PollOption> getPollOptionByPollId(Long id);

    public PollOption addPollOption(PollOption pollOption);

    public Poll getPollById(Long id);

    public Map<String, Object> getPollOptionResponse(PollOption pollOption);
}
