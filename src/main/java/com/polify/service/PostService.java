package com.polify.service;

import com.polify.entity.Community;
import com.polify.entity.Post;
import com.polify.entity.User;

import java.util.List;

public interface PostService {

    public List<Post> getPost();

    public void addPost(Post post);

    public User getUserById(Long id);

    public Community getCommunityById(Long id);
}
