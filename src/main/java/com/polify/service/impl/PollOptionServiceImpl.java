package com.polify.service.impl;

import com.polify.entity.Poll;
import com.polify.entity.PollOption;
import com.polify.entity.User;
import com.polify.repository.PollOptionRepository;
import com.polify.repository.PollRepository;
import com.polify.repository.UserRepository;
import com.polify.service.PollOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PollOptionServiceImpl implements PollOptionService {


    @Autowired
    private PollOptionRepository pollOptionRepository;

    @Autowired
    private PollRepository pollRepository;


    @Override
    public List<PollOption> getPollOption() {
        return pollOptionRepository.findAll();
    }

    @Override
    public void addPollOption(PollOption pollOption) {
        pollOptionRepository.save(pollOption);
    }

    @Override
    public Poll getPollById(Long id) {
        return pollRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Poll not found!!!"));
    }
}
