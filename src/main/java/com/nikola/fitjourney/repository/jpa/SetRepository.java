package com.nikola.fitjourney.repository.jpa;

import com.nikola.fitjourney.model.ExerciseSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SetRepository extends JpaRepository<ExerciseSet,Long> {
}
