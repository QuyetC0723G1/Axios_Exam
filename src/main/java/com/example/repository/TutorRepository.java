package com.example.repository;

import com.example.model.Student;
import com.example.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TutorRepository extends JpaRepository<Tutor,Long> {

}
