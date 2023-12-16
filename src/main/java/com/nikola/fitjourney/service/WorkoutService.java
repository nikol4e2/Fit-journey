package com.nikola.fitjourney.service;

import com.nikola.fitjourney.model.DoneExercise;
import com.nikola.fitjourney.model.Exercise;
import com.nikola.fitjourney.model.User;
import com.nikola.fitjourney.model.Workout;

import java.util.Optional;

public interface WorkoutService {

    Workout save(String name, User user);
    void addDoneExercise(Long workoutId, DoneExercise exercise);
    void deleteById(Long id);
    void deleteDoneExercise(Long workoutId,Long exerciseId);
    Optional<Workout> findById(Long id);
    double calculateTotalVolume(Long workoutId);
    Workout update(Workout workout);
}
