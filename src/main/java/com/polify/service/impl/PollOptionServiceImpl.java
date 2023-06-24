package com.polify.service.impl;

import com.polify.entity.Poll;
import com.polify.entity.PollOption;
import com.polify.repository.PollRepository;
import com.polify.repository.PollOptionRepository;
import com.polify.service.PollOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PollOptionServiceImpl implements PollOptionService {


    @Autowired
    private PollOptionRepository pollOptionRepository;

    @Autowired
    private PollRepository pollRepository;


    @Override
    public List<PollOption> getPollOptionByPollId(Long id) {
        return pollOptionRepository.findByPollId(id);
    }

    @Override
    public PollOption addPollOption(PollOption pollOption) {
        return pollOptionRepository.save(pollOption);
    }

    @Override
    public Poll getPollById(Long id) {
        return pollRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Poll not found!!!"));
    }

    @Override
    public Map<String, Object> getPollOptionResponse(PollOption pollOption) {
        Map<String, Object> pollOptionMap = new HashMap<>();

        pollOptionMap.put("id", pollOption.getId());
        pollOptionMap.put("optionText", pollOption.getOptionText());
        pollOptionMap.put("percentage", pollOption.getPercentage());

        return pollOptionMap;
    }
}
