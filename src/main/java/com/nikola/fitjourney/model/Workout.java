package com.nikola.fitjourney.model;

import com.nikola.fitjourney.model.enumerations.WorkoutStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;

import java.time.LocalDate;
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
    private LocalDate date;
    @ManyToMany(cascade= CascadeType.ALL)
    private List<DoneExercise> exercises;
    private WorkoutStatus status;
    private double totalVolume;
    private String feeling;
    @ManyToOne
    private User user;


    public Workout(String name, LocalDate date,User user) {
        this.name = name;
        this.date = date;
        this.totalVolume = 0;
        this.feeling = "";
        this.status=WorkoutStatus.STARTED;
        this.exercises=new ArrayList<>();
        this.user=user;

    }

    public Workout() {
    }
}
