package com.nikola.fitjourney.service.impl;

import com.nikola.fitjourney.model.DoneExercise;
import com.nikola.fitjourney.model.Exercise;
import com.nikola.fitjourney.model.Set;
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
    public Optional<DoneExercise> save(Exercise exercise) {
        return repository.save(exercise);
    }

    @Override
    public void addSet(Long id, Set set) {
        if(this.repository.findById(id).isPresent()) {
            DoneExercise doneExercise = repository.findById(id).get();
            doneExercise.getSets().add(set);
        }else throw new ExerciseDoesNotExistException(id);

    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
}
