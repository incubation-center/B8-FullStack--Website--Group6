package com.polify.controller;

import com.polify.entity.Vote;
import com.polify.model.VoteDTO;
import com.polify.repository.VoteRepository;
import com.polify.service.VoteService;
import com.polify.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ProjectUtils.VOTE_URL)
public class VoteController {

    @Autowired
    private VoteService voteService;

    @GetMapping
    public List<Vote> getVote(){
        return voteService.getVote();
    }

    @PostMapping
    public void addVote(@RequestBody VoteDTO voteDTO){
        Vote vote = new Vote();
        vote.setOption(voteService.getPollOptionById(voteDTO.getOption_id()));
        vote.setUsers(voteService.getUserById(voteDTO.getUser_id()));
        voteService.addVote(vote);
    }
}
