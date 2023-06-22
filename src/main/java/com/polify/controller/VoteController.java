package com.polify.controller;

import com.polify.entity.PollOption;
import com.polify.entity.User;
import com.polify.entity.Vote;
import com.polify.model.VoteDTO;
import com.polify.repository.VoteRepository;
import com.polify.service.PollOptionService;
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

    @Autowired
    private PollOptionService pollOptionService;

    @PostMapping(path = "{poll_id}")
    public void addVote(@RequestBody VoteDTO voteDTO, @PathVariable Long poll_id){
        User user = voteService.getUserById(voteDTO.getUser_id());

        long total = pollOptionService.countTotalVoted(poll_id) + voteDTO.getOption_id().size();
        for (Long optionId: voteDTO.getOption_id()){
            Vote vote = new Vote();
            vote.setUsers(user);
            vote.setOption(voteService.getPollOptionById(optionId));
            voteService.addVote(vote);

            PollOption pollOption = voteService.getPollOptionById(optionId);
            pollOption.setOptionVoted(pollOption.getOptionVoted() + 1);
            pollOption.setPercentage((float)pollOption.getOptionVoted() / total);
            pollOptionService.addPollOption(pollOption);
        }

    }
}
