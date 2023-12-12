package com.nikola.fitjourney.model;


import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
public class User {

    private String username;
    private String password;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private double Weight;


    public User(String username, String password, String name, String surname, Date dateOfBirth, double weight) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        Weight = weight;
    }
}
