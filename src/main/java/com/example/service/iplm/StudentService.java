package com.example.service.iplm;

import com.example.model.Student;
import com.example.repository.StudentRepository;
import com.example.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService implements IStudentService {
    @Autowired
    StudentRepository studentRepository;
    @Override
    public Iterable<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findOneById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Iterable<Student> findByName(String name) {
        return studentRepository.findByNameContainsIgnoreCase(name);
    }
}
