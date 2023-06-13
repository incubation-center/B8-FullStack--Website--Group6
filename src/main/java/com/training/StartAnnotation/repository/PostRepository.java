package com.training.StartAnnotation.repository;

import com.training.StartAnnotation.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
