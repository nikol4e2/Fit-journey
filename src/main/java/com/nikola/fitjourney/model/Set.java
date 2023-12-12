package com.nikola.fitjourney.model;

import lombok.Data;

@Data
public class Set {

    private Long id;
    private int reps;
    private double weight;

    public Set(int reps, double weight) {
        this.reps = reps;
        this.weight = weight;
    }
}
