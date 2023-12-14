package com.nikola.fitjourney.service;

import com.nikola.fitjourney.model.Exercise;
import com.nikola.fitjourney.model.enumerations.MuscleType;

import java.util.List;
import java.util.Optional;

public interface ExerciseService {

    List<Exercise> findAll();
    List<Exercise> findAllByPrimaryMuscle(MuscleType muscleType);
    Optional<Exercise> findByName(String name);
    Optional<Exercise> findById(Long id);
    Exercise save(String name, MuscleType muscleType);
    void deleteById(Long id);

}
