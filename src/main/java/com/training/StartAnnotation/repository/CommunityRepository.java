package com.training.StartAnnotation.repository;

import com.training.StartAnnotation.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community,Integer> {
}
