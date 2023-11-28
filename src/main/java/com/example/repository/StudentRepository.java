package com.example.repository;

import com.example.model.ClassRoom;
import com.example.model.Student;
import com.example.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Iterable<Student> findByNameContainsIgnoreCase(String name);
    Iterable<Student> findAllByClassRoomIdIn(List<Long> classRooms);

    Iterable<Student> findByTutorsIdIn(List<Long> tutorsId);

}
