package com.nikola.fitjourney.model;

import com.nikola.fitjourney.model.enumerations.WorkoutStatus;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Workout {
    private Long id;
    private String name;
    private Date date;
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
    
}
