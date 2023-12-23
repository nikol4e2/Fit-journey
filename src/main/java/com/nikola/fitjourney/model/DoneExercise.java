package com.nikola.fitjourney.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class DoneExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Exercise exercise;

    @OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
    List<ExerciseSet> sets;

    public DoneExercise(Exercise exercise) {
        this.exercise = exercise;
        this.sets=new ArrayList<>();
    }


    public DoneExercise() {
    }
}
