package com.polify.controller;

import com.polify.entity.PollOption;
import com.polify.entity.User;
import com.polify.entity.Vote;
import com.polify.model.VoteDTO;
import com.polify.service.PollOptionService;
import com.polify.service.PollService;
import com.polify.service.UserAccountService;
import com.polify.service.VoteService;
import com.polify.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(ProjectUtils.VOTE_URL)
public class VoteController {

    @Autowired
    private VoteService voteService;

    @Autowired
    private PollOptionService pollOptionService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private PollService pollService;

    @PostMapping(path = "{poll_id}")
    public ResponseEntity<Map<String, Object>> addVote(@RequestBody VoteDTO voteDTO, @PathVariable Long poll_id, Authentication authentication){

        String username = authentication.getName();
        User user = userAccountService.getUserByUsername(username);

        for (Long optionId: voteDTO.getOption_id()){
            Vote vote = new Vote();
            vote.setUsers(user);
            vote.setOption(voteService.getPollOptionById(optionId));
            voteService.addVote(vote);

            PollOption pollOption = voteService.getPollOptionById(optionId);
            pollOption.setOptionVoted(pollOption.getOptionVoted() + 1);
            pollOptionService.addPollOption(pollOption);
        }

        List<PollOption> pollOptions = pollOptionService.getPollOptionByPollId(poll_id);
        Long total = 0L;
        for (PollOption pollOption: pollOptions) {
            total += pollOption.getOptionVoted();
        }

        List<Map<String, Object>> options = new ArrayList<>();
        for (PollOption pollOption: pollOptions) {
            pollOption.setPercentage((float) pollOption.getOptionVoted() / total);
            PollOption savedPolloption = pollOptionService.addPollOption(pollOption);
            Map<String, Object> pollOptionMap = pollOptionService.getPollOptionResponse(savedPolloption);
            options.add(pollOptionMap);
        }

        Map<String, Object> pollMap = pollService.getPollResponse(voteService.getPollByPollId(poll_id));
        pollMap.put("options", options);

        return ResponseEntity.ok(pollMap);
    }
}
