package com.example.service;

import com.example.model.ClassRoom;
import com.example.model.Student;
import com.example.model.Tutor;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface IStudentService extends IGenerateService<Student> {
    Iterable<Student> findByName(String name);
    Iterable<Student> findByClassRoom(List<Long> classRooms);
    Iterable<Student> findByTutorsIdIn(List<Long> tutorsId);
}
