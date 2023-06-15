package com.training.StartAnnotation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.training.StartAnnotation.entity.*;

@Service
public class StudentServiceImplV2 implements StudentService{

    @Override
    public Student addStudent(Student student) {
        return null;
    }

    @Override
    public Optional<Student> getStudent(int id) {
        return Optional.empty();
    }

    @Override
    public List<Student> getStudents() {
        return null;
    }
}
