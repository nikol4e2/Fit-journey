package com.nikola.fitjourney.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Set {
    @Id
    @GeneratedValue
    private Long id;
    private int reps;
    private double weight;

    public Set(int reps, double weight) {
        this.reps = reps;
        this.weight = weight;
    }

    public Set() {
    }
}
