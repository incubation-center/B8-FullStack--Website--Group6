package com.polify.controller;

import com.polify.entity.User;
import com.polify.service.UserAccountService;
import com.polify.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ProjectUtils.USER_URL)
public class UserController {

    @Autowired
    private UserAccountService userAccountService;

    @GetMapping("/all")
    public List<User> getAllUser(){
        return userAccountService.getAllUser();
    }
}
