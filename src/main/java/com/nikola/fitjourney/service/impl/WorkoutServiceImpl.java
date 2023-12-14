package com.nikola.fitjourney.service.impl;

import com.nikola.fitjourney.model.DoneExercise;
import com.nikola.fitjourney.model.Exercise;
import com.nikola.fitjourney.model.Workout;
import com.nikola.fitjourney.repository.jpa.WorkoutRepository;
import com.nikola.fitjourney.service.DoneExerciseService;
import com.nikola.fitjourney.service.WorkoutService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final DoneExerciseService doneExerciseService;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository, DoneExerciseService doneExerciseService) {
        this.workoutRepository = workoutRepository;
        this.doneExerciseService = doneExerciseService;
    }

    @Override
    public Workout save(String name) {
        return this.workoutRepository.save(new Workout(name, LocalDate.now()));
    }

    @Override
    public void addDoneExercise(Long workoutId, DoneExercise exercise) {
        if(this.workoutRepository.findById(workoutId).isPresent())
        {
            Workout workout=this.workoutRepository.findById(workoutId).get();
            workout.getExercises().add(exercise);
        }

    }

    @Override
    public void deleteById(Long id) {
        this.workoutRepository.deleteById(id);
    }

    @Override
    public void deleteDoneExercise(Long workoutId,Long exerciseId) {
        if(this.workoutRepository.findById(workoutId).isPresent())
        {
            Workout workout=this.workoutRepository.findById(workoutId).get();
            workout.getExercises().removeIf(r->r.getId().equals(exerciseId));
        }
    }

    @Override
    public Optional<Workout> findById(Long id) {
        return this.workoutRepository.findById(id);
    }
}
