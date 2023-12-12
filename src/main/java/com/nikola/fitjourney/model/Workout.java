package com.nikola.fitjourney.model;

import com.nikola.fitjourney.model.enumerations.WorkoutStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Workout {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Date date;
    @ManyToMany
    private List<Exercise> exercises;
    private WorkoutStatus status;
    private double totalVolume;
    private String feeling;


    public Workout(String name, Date date) {
        this.name = name;
        this.date = date;
        this.totalVolume = 0;
        this.feeling = "";
        this.status=WorkoutStatus.STARTED;
        this.exercises=new ArrayList<>();

    }

    public Workout() {
    }
}
