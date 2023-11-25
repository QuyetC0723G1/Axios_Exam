package com.example.controller;

import com.example.model.ClassRoom;
import com.example.service.iplm.ClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/classRooms")
@CrossOrigin("*")
public class ClassRoomController {
    @Autowired
    private ClassRoomService classRoomService;

    @GetMapping("")
    public ResponseEntity<List<ClassRoom>> getAllClass(){
        List<ClassRoom> classRooms = (List<ClassRoom>) classRoomService.findAll();
        if (classRooms.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(classRooms,HttpStatus.OK);
    }
}
