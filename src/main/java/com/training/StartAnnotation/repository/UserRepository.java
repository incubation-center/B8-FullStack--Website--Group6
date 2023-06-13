package com.training.StartAnnotation.repository;

import com.training.StartAnnotation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
