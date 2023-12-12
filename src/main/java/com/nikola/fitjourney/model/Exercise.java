package com.nikola.fitjourney.model;

import com.nikola.fitjourney.model.enumerations.MuscleType;
import lombok.Data;

@Data
public class Exercise {

    private Long id;
    private String name;
    private MuscleType primaryMuscle;

    public Exercise(String name, MuscleType primaryMuscle) {
        this.name = name;
        this.primaryMuscle = primaryMuscle;
    }
}
