package com.training.StartAnnotation.repository;

import com.training.StartAnnotation.entity.PollOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PollOptionRepository extends JpaRepository<PollOption,Integer> {
}
