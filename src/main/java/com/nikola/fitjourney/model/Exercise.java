package com.nikola.fitjourney.model;

import com.nikola.fitjourney.model.enumerations.MuscleType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Exercise {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private MuscleType primaryMuscle;

    public Exercise(String name, MuscleType primaryMuscle) {
        this.name = name;
        this.primaryMuscle = primaryMuscle;
    }

    public Exercise() {
    }
}
