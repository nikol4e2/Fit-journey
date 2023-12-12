package com.nikola.fitjourney.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class DoneExercise {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Exercise exercise;

    @OneToMany
    List<Set> sets;

    public DoneExercise(Exercise exercise) {
        this.exercise = exercise;
        this.sets=new ArrayList<>();
    }

    public DoneExercise() {
    }
}
