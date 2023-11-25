package com.example.service.iplm;

import com.example.model.ClassRoom;
import com.example.repository.ClassRoomRepository;
import com.example.service.IClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClassRoomService implements IClassRoomService {
    @Autowired
    ClassRoomRepository classRoomRepository;
    @Override
    public Iterable<ClassRoom> findAll() {
        return classRoomRepository.findAll();
    }

    @Override
    public Optional<ClassRoom> findOneById(Long id) {
        return classRoomRepository.findById(id);
    }

    @Override
    public ClassRoom save(ClassRoom classRoom) {
        return classRoomRepository.save(classRoom);
    }

    @Override
    public void delete(Long id) {
        classRoomRepository.deleteById(id);
    }
}
