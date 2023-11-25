package com.example.service;

import com.example.model.Student;

public interface IStudentService extends IGenerateService<Student> {
    Iterable<Student> findByName(String name);
}
