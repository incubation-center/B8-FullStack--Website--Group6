package com.polify.controller;

import com.polify.entity.Poll;
import com.polify.entity.PollOption;
import com.polify.model.PollDTO;
import com.polify.service.PollOptionService;
import com.polify.service.PollService;
import com.polify.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ProjectUtils.POLL_URL)
public class PollController {

    @Autowired
    private PollService pollService;

    @Autowired
    private PollOptionService pollOptionService;

    @GetMapping(path = "{community_id}")
    public List<Map<String, Object>> getPoll(@PathVariable Long community_id){
        List<Map<String, Object>> pollResult = new ArrayList<>();
        List<Poll> pollList = pollService.getPoll(community_id);

        for (Poll poll: pollList){
            Map<String, Object> pollMap = new HashMap<>();
            pollMap.put("id", poll.getId());
            pollMap.put("pollQuestion", poll.getPollQuestion());
            pollMap.put("limitVote", poll.getLimitVote());
            pollMap.put("duration", poll.getDuration());
            pollMap.put("community", poll.getCommunity());
            pollMap.put("pollDate", poll.getPollDate());
            pollMap.put("user", poll.getUser());


            Map<String, Float> options = new HashMap<>();
            for (PollOption pollOption: pollOptionService.getPollOptionByPollId(poll.getId())) {
                options.put(pollOption.getOptionText(), pollOption.getPercentage());
            }
            pollMap.put("options", options);
            pollResult.add(pollMap);
        }

        return pollResult;
    }

    @PostMapping(path = "{id}")
    public void addPoll(@RequestBody PollDTO pollDTO, @PathVariable Long id){
        Poll poll = new Poll();

        poll.setPollQuestion(pollDTO.getPollQuestion());
        poll.setLimitVote(pollDTO.getLimitVote());
        poll.setDuration(pollDTO.getDuration());
        poll.setCommunity(pollService.getCommunityById(id));
        poll.setUser(pollService.getUserById(pollDTO.getUser_id()));

        Poll savedPoll = pollService.addPoll(poll);

        for (String optionText: pollDTO.getOptions()) {
            PollOption pollOption = new PollOption();
            pollOption.setOptionText(optionText);
            pollOption.setPoll(pollOptionService.getPollById(savedPoll.getId()));
            pollOptionService.addPollOption(pollOption);
        }
    }
}
