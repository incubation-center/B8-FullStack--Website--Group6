package com.polify.controller;


import com.polify.entity.PollOption;
import com.polify.model.PollOptionDTO;
import com.polify.service.PollOptionService;
import com.polify.utils.ProjectUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ProjectUtils.POLL_OPTION_URL)
public class PollOptionController {

    @Autowired
    private PollOptionService pollOptionService;


    @PostMapping
    public void addPollOption(@RequestBody PollOptionDTO pollOptionDTO) {
        PollOption pollOption = new PollOption();

        pollOption.setOptionText(pollOptionDTO.getOptionText());
        pollOption.setPoll(pollOptionService.getPollById(pollOptionDTO.getPoll_id()));

        pollOptionService.addPollOption(pollOption);
    }
}
