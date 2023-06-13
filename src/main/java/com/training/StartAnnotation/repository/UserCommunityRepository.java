package com.training.StartAnnotation.repository;

import com.training.StartAnnotation.entity.UserCommunity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCommunityRepository extends JpaRepository<UserCommunity,Integer> {
}
