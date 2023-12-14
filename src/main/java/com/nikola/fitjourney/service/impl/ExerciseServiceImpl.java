package com.nikola.fitjourney.service.impl;

import com.nikola.fitjourney.model.Exercise;
import com.nikola.fitjourney.model.enumerations.MuscleType;
import com.nikola.fitjourney.repository.jpa.ExerciseRepository;
import com.nikola.fitjourney.service.ExerciseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    @Override
    public List<Exercise> findAllByPrimaryMuscle(MuscleType muscleType) {
        return exerciseRepository.findAllByPrimaryMuscle(muscleType);
    }

    @Override
    public Optional<Exercise> findByName(String name) {
        return this.exerciseRepository.findByName(name);
    }

    @Override
    public Optional<Exercise> findById(Long id) {
        return this.exerciseRepository.findById(id);
    }

    @Override
    public Exercise save(String name, MuscleType muscleType) {
        return this.exerciseRepository.saveAndFlush(new Exercise(name,muscleType));
    }

    @Override
    public void deleteById(Long id) {

        this.exerciseRepository.deleteById(id);

    }

}
