package com.training.StartAnnotation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.training.StartAnnotation.entity.*;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
}
	