package com.nikola.fitjourney.model;

import com.nikola.fitjourney.model.enumerations.WorkoutStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="workoutId", unique = true, nullable = false)
    private Long workoutId;
    private String name;
    private LocalDate workoutDate;

    @OneToMany(cascade= CascadeType.ALL,fetch = FetchType.EAGER)
    private List<DoneExercise> exercises;

    private WorkoutStatus status;
    private double totalVolume;
    private String feeling;
 //   @ManyToOne(cascade = CascadeType.REMOVE)
  //  private User user;


    public Workout(String name, LocalDate workoutDate) {
        this.name = name;
        this.workoutDate = workoutDate;
        this.totalVolume = 0;
        this.feeling = "";
        this.status=WorkoutStatus.STARTED;
        this.exercises=new ArrayList<>();
   //     this.user=user;

    }

    public Workout() {
    }


}
