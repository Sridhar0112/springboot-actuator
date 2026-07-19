package com.sridhar.springboot.health.service;

import com.sridhar.springboot.Repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentHealthService {

    private final StudentRepository studentRepository;

    public StudentHealthService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public long getStudentCount() {
        return studentRepository.count();
    }
}