package com.polify.controller;

import com.polify.entity.Poll;
import com.polify.model.PollDTO;
import com.polify.service.PollService;
import com.polify.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ProjectUtils.POLL_URL)
public class PollController {

    @Autowired
    private PollService pollService;

    @GetMapping
    public List<Poll> getPoll(){
        return pollService.getPoll();
    }

    @PostMapping
    public void addPoll(@RequestBody PollDTO pollDTO){
        Poll poll = new Poll();

        poll.setPollQuestion(pollDTO.getPollQuestion());
        poll.setPollDescription(pollDTO.getPollDescription());
        poll.setPost(pollService.getPostById(pollDTO.getPost_id()));
        pollService.addPoll(poll);
    }
}
