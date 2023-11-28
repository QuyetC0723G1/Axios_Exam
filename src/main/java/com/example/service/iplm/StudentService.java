package com.example.service.iplm;

import com.example.model.ClassRoom;
import com.example.model.Student;
import com.example.model.Tutor;
import com.example.repository.StudentRepository;
import com.example.repository.TutorRepository;
import com.example.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService implements IStudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private TutorRepository tutorRepository;
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
        Set<Tutor> tutors = student.getTutors();
        if (tutors != null && !tutors.isEmpty()){
            Set<Tutor> managedTutors = new HashSet<>();
            for (Tutor tutor: tutors) {
                if (tutor.getId() != null){
                    Optional<Tutor> optionalTutor = tutorRepository.findById(tutor.getId());
                    optionalTutor.ifPresent(managedTutors::add);
                }
            }student.setTutors(managedTutors);
        }
        return studentRepository.save(student);
    }

    @Override
    public void delete(Long id) {
        Optional<Student> student = findOneById(id);
            student.get().setTutors(new HashSet<>());
        studentRepository.deleteById(id);
    }

    @Override
    public Iterable<Student> findByName(String name) {
        return studentRepository.findByNameContainsIgnoreCase(name);
    }

    @Override
    public Iterable<Student> findByClassRoom(List<Long> classRooms) {
        return  studentRepository.findAllByClassRoomIdIn(classRooms);
    }

    @Override
    public Iterable<Student> findByTutorsIdIn(List<Long> tutors) {
        return studentRepository.findByTutorsIdIn(tutors);
    }

}
