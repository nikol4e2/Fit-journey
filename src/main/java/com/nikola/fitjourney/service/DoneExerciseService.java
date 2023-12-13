package com.nikola.fitjourney.service;

import com.nikola.fitjourney.model.DoneExercise;
import com.nikola.fitjourney.model.Exercise;
import com.nikola.fitjourney.model.Set;

import java.util.Optional;


public interface DoneExerciseService {

    Optional<DoneExercise> save(Exercise exercise);
    void addSet(Long id,Set set);
    void deleteById(Long id);
}
