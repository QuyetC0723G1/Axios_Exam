package com.example.service.iplm;

import com.example.model.Tutor;
import com.example.repository.TutorRepository;
import com.example.service.ITutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class TutorService implements ITutorService {
    @Autowired
    TutorRepository tutorRepository;
    @Override
    public Iterable<Tutor> findAll() {
        return tutorRepository.findAll();
    }

    @Override
    public Optional<Tutor> findOneById(Long id) {
        return tutorRepository.findById(id);
    }

    @Override
    public Tutor save(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    @Override
    public void delete(Long id) {
        tutorRepository.deleteById(id);
    }
}
