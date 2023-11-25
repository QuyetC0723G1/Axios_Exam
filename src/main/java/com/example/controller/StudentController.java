package com.example.controller;

import com.example.model.Student;
import com.example.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("")
    public ResponseEntity<Student> save(@RequestBody Student student) {
        return new ResponseEntity<>(studentService.save(student), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> delete(@PathVariable Long id) {
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
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

    @GetMapping("{id}")
    public ResponseEntity<Student> getInfo(@PathVariable Long id) {
        Optional<Student> student = studentService.findOneById(id);
        return new ResponseEntity<>(student.get(), HttpStatus.OK);

    }


}
