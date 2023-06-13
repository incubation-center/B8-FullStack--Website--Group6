package com.training.StartAnnotation.repository;

import com.training.StartAnnotation.entity.OptionVoted;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionVotedRepository extends JpaRepository<OptionVoted,Integer> {
}
