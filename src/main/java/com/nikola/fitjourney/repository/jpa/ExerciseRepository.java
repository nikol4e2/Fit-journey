package com.nikola.fitjourney.repository.jpa;

import com.nikola.fitjourney.model.Exercise;
import com.nikola.fitjourney.model.enumerations.MuscleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.desktop.OpenFilesEvent;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise,Long> {

    Optional<Exercise> findByName(String name);
    void deleteByName(String name);
    List<Exercise> findAllByPrimaryMuscle(MuscleType muscleType);

}
