package com.polify.service.impl;

import com.polify.entity.Poll;
import com.polify.entity.PollOption;
import com.polify.repository.PollRepository;
import com.polify.repository.PollOptionRepository;
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
    public List<PollOption> getPollOptionByPollId(Long id) {
        return pollOptionRepository.findByPollId(id);
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

    @Override
    public Long countTotalVoted(Long pollId) {
        List<PollOption> pollOptions = pollOptionRepository.findByPollId(pollId);
        Long total = 0L;
        for (PollOption pollOption: pollOptions) {
            total += pollOption.getOptionVoted();
        }

        return total;
    }
}
