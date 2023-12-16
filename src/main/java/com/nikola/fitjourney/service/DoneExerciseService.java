package com.nikola.fitjourney.service;

import com.nikola.fitjourney.model.DoneExercise;
import com.nikola.fitjourney.model.Exercise;
import com.nikola.fitjourney.model.ExerciseSet;

import java.util.Optional;


public interface DoneExerciseService {

    Optional<DoneExercise> save(Exercise exercise);
    void addSet(Long id, ExerciseSet set);
    void deleteById(Long id);
    Optional<DoneExercise> findById(Long id);
    Optional<DoneExercise> update(DoneExercise doneExercise);
}
