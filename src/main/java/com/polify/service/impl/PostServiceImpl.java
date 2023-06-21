package com.polify.service.impl;

import com.polify.entity.Community;
import com.polify.entity.Post;
import com.polify.entity.User;
import com.polify.repository.CommunityRepository;
import com.polify.repository.PostRepository;
import com.polify.repository.UserRepository;
import com.polify.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommunityRepository communityRepository;

    @Override
    public List<Post> getPost() {
        return postRepository.findAll();
    }

    @Override
    public void addPost(Post post) {
        postRepository.save(post);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found!!!"));
    }

    @Override
    public Community getCommunityById(Long id) {
        return communityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Community Not Found!!!"));
    }
}
