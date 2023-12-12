package com.nikola.fitjourney.service;

import com.nikola.fitjourney.model.User;

import java.util.Date;

public interface AuthService {
    User login(String username, String password);
    User register(String username, String password, String repeatPassword, String name, String surname, Date dateOfBirth, double weight );
}
