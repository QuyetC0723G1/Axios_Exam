package com.example.controller;

import com.example.model.ClassRoom;
import com.example.model.Student;
import com.example.repository.TutorRepository;
import com.example.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/students")
@CrossOrigin("*")
public class StudentController {
    @Autowired
    private IStudentService studentService;


    @GetMapping("")
    public ResponseEntity<List<Student>> getAll() {
        List<Student> students = (List<Student>) studentService.findAll();
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Student> save(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.save(student), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Student> delete(@PathVariable Long id) {
        studentService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student) {
        student.setId(id);

        studentService.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Student>> search(@RequestParam String name) {
        List<Student> students = (List<Student>) studentService.findByName(name);
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);

    }

    @GetMapping("/update/{id}")
    public ResponseEntity<Student> getInfo(@PathVariable Long id) {
        Optional<Student> student = studentService.findOneById(id);
        return new ResponseEntity<>(student.get(), HttpStatus.OK);
    }

    @PostMapping("/find")
    public ResponseEntity<List<Student>> findByClassRoom(@RequestBody List<Long> classRoomsId){
        if (classRoomsId == null || classRoomsId.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Student> students = (List<Student>) studentService.findByClassRoom(classRoomsId);
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("/findByTutors")
    public ResponseEntity<List<Student>> findByTutorsId(@RequestBody List<Long> tutorsId){
        if (tutorsId == null || tutorsId.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Student> students = (List<Student>) studentService.findByTutorsIdIn(tutorsId);
        if (students.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

}
