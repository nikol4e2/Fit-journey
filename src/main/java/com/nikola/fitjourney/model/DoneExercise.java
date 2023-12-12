package com.nikola.fitjourney.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DoneExercise {
    private Long id;
    private Exercise exercise;
    List<Set> sets;

    public DoneExercise(Exercise exercise) {
        this.exercise = exercise;
        this.sets=new ArrayList<>();
    }
}
