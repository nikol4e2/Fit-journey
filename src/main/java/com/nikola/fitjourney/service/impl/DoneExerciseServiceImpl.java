package com.nikola.fitjourney.service.impl;

import com.nikola.fitjourney.model.DoneExercise;
import com.nikola.fitjourney.model.Exercise;
import com.nikola.fitjourney.model.ExerciseSet;
import com.nikola.fitjourney.model.exceptions.ExerciseDoesNotExistException;
import com.nikola.fitjourney.repository.jpa.DoneExerciseRepository;
import com.nikola.fitjourney.service.DoneExerciseService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoneExerciseServiceImpl implements DoneExerciseService {

    private final DoneExerciseRepository repository;

    public DoneExerciseServiceImpl(DoneExerciseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<DoneExercise> save(DoneExercise doneExercise) {
        return Optional.of(repository.save(doneExercise));
    }

    @Override
    public void addSet(Long id, ExerciseSet set) {
        if(this.repository.findById(id).isPresent()) {
            DoneExercise doneExercise = repository.findById(id).get();
            doneExercise.getSets().add(set);
        }else throw new ExerciseDoesNotExistException(id);

    }

    @Override
    public Optional<DoneExercise> findById(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public Optional<DoneExercise> update(DoneExercise doneExercise) {
        return Optional.of(this.repository.save(doneExercise));
    }
}
