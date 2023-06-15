package com.training.StartAnnotation.service;

import com.training.StartAnnotation.entity.*;

import java.util.List;
import java.util.Optional;


public interface StudentService {

    public Student addStudent(Student student);

    public Optional<Student> getStudent(int id);

    public List<Student> getStudents();
}
