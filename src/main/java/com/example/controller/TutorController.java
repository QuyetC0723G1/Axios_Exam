package com.example.controller;

import com.example.model.Tutor;
import com.example.service.iplm.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tutors")
@CrossOrigin("*")
public class TutorController {
    @Autowired
    private TutorService tutorService;

    @GetMapping("")
    public ResponseEntity<List<Tutor>> showAllTutor(){
        List<Tutor> tutors = (List<Tutor>) tutorService.findAll();
        if (tutors.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(tutors,HttpStatus.OK);
    }

}
