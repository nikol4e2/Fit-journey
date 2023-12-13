package com.nikola.fitjourney.service.impl;

import com.nikola.fitjourney.model.ExerciseSet;
import com.nikola.fitjourney.repository.jpa.SetRepository;
import com.nikola.fitjourney.service.SetService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SetServiceImpl implements SetService {

    private final SetRepository setRepository;

    public SetServiceImpl(SetRepository setRepository) {
        this.setRepository = setRepository;
    }

    @Override
    public ExerciseSet save(int reps, Double weight) {
        return setRepository.save(new ExerciseSet(reps,weight));
    }

    @Override
    public void deleteById(Long id) {
        this.setRepository.deleteById(id);
    }

    @Override
    public Optional<ExerciseSet> findById(Long id) {
        return this.setRepository.findById(id);
    }
}
