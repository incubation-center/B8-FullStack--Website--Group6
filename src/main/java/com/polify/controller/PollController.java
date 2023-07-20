package com.polify.controller;

import com.polify.entity.Poll;
import com.polify.entity.PollOption;
import com.polify.entity.User;
import com.polify.model.PollDTO;
import com.polify.service.PollOptionService;
import com.polify.service.PollService;
import com.polify.service.UserAccountService;
import com.polify.service.UserService;
import com.polify.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

@RestController
@RequestMapping(ProjectUtils.POLL_URL)
public class PollController {

    @Autowired
    private PollService pollService;

    @Autowired
    private PollOptionService pollOptionService;
    @Autowired
    private UserAccountService userAccountService;

    @GetMapping(path = "community/{community_id}")
    public List<Map<String, Object>> getCommunityPoll(@PathVariable UUID community_id, Authentication authentication){
        List<Map<String, Object>> pollResult = new ArrayList<>();
        List<Poll> pollList = pollService.getCommunityPoll(community_id);

        String username = authentication.getName();
        User user = userAccountService.getUserByUsername(username);

        for (Poll poll: pollList){
            Map<String, Object> pollMap = pollService.getPollResponse(poll, user);


            List<Map<String, Object>> options = new ArrayList<>();
            for (PollOption pollOption: pollOptionService.getPollOptionByPollId(poll.getId())) {
                Map<String, Object> pollOptionMap = pollOptionService.getPollOptionResponse(pollOption);

                options.add(pollOptionMap);
            }

            pollMap.put("options", options);
            pollResult.add(pollMap);
        }

        return pollResult;
    }

    @PostMapping(path = "community/{community_id}")
    public ResponseEntity<Map<String, Object>> addPoll(@RequestBody PollDTO pollDTO, @PathVariable UUID community_id, Authentication authentication){

        Poll poll = new Poll();

        poll.setPollQuestion(pollDTO.getPollQuestion());
        poll.setLimitVote(pollDTO.getLimitVote());
        poll.setDuration(pollDTO.getDuration());
        poll.setCommunity(pollService.getCommunityById(community_id));
        poll.setTotalVote(0L);

        String username = authentication.getName();
        User user = userAccountService.getUserByUsername(username);

        poll.setUser(pollService.getUserById(user.getId()));

        Poll savedPoll = pollService.addPoll(poll);
        List<Map<String, Object>> options = new ArrayList<>();
        for (String optionText: pollDTO.getOptions()) {
            PollOption pollOption = new PollOption();
            pollOption.setOptionText(optionText);
            pollOption.setPoll(pollOptionService.getPollById(savedPoll.getId()));
            PollOption savedPollOption = pollOptionService.addPollOption(pollOption);

            Map<String, Object> pollOptionMap = pollOptionService.getPollOptionResponse(savedPollOption);
            options.add(pollOptionMap);
        }

        Map<String, Object> pollMap = pollService.getPollResponse(savedPoll, user);
        pollMap.put("options", options);

        return ResponseEntity.ok(pollMap);
    }

    @GetMapping(path = "/stream/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Map<String, Object>> getPoll(@PathVariable Long id, @RequestParam("email") String email) {

        User user = userAccountService.getByEmail(email);

        Poll poll = pollService.getPoll(id);
        Map<String, Object> pollMap = pollService.getPollResponse(poll, user);
        List<Map<String, Object>> options = new ArrayList<>();
        for (PollOption pollOption: pollOptionService.getPollOptionByPollId(poll.getId())) {
            Map<String, Object> pollOptionMap = pollOptionService.getPollOptionResponse(pollOption);

            options.add(pollOptionMap);
        }
        pollMap.put("options", options);

        System.out.println(pollMap);

        //TODO: Replace this with your file path
        Stream<Map<String, Object>> stream = Stream.of(pollMap);
        return Flux.fromStream(stream)
            .delayElements(Duration.ofMillis(300));
    }
}
