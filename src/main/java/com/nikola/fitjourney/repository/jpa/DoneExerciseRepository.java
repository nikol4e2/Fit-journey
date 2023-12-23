package com.nikola.fitjourney.repository.jpa;

import com.nikola.fitjourney.model.DoneExercise;
import com.nikola.fitjourney.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoneExerciseRepository extends JpaRepository<DoneExercise,Long> {



}
