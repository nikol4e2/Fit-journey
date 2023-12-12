package com.nikola.fitjourney.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@Entity(name = "app-user")
public class User {
    @Id
    private String username;
    private String password;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private double Weight;

    @OneToMany
    private List<Workout> workoutsDone;


    public User(String username, String password, String name, String surname, Date dateOfBirth, double weight) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.Weight = weight;
        this.workoutsDone=new ArrayList<>();
    }

    public User() {
    }
}
