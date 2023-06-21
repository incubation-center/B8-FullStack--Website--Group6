package com.polify.controller;

import com.polify.entity.Post;
import com.polify.model.PostDTO;
import com.polify.service.PostService;
import com.polify.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ProjectUtils.POST_URL)
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getPost(){
        return postService.getPost();
    }

    @PostMapping
    public void addPost(@RequestBody PostDTO postDTO){
        Post post = new Post();

        post.setPostContent(postDTO.getPostContent());

        post.setUsers(postService.getUserById(postDTO.getUser_id()));

        post.setCommunity(postService.getCommunityById(postDTO.getCommunity_id()));

        postService.addPost(post);
    }
}
