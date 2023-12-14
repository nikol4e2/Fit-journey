package com.nikola.fitjourney.service.impl;

import com.nikola.fitjourney.model.User;
import com.nikola.fitjourney.model.Workout;
import com.nikola.fitjourney.model.exceptions.InvalidUserCredentialsException;
import com.nikola.fitjourney.model.exceptions.PasswordsDoNotMatchException;
import com.nikola.fitjourney.model.exceptions.UserNameAlreadyExistsException;
import com.nikola.fitjourney.repository.jpa.UserRepository;
import com.nikola.fitjourney.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if(username == null || username.isEmpty() || password==null || password.isEmpty())
        {
            throw new InvalidUserCredentialsException();
        }
        return userRepository.findByUsernameAndPassword(username,password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname, Date dateOfBirth, double weight) {
        if(username == null || username.isEmpty() || password==null || password.isEmpty())
        {
            throw new InvalidUserCredentialsException();
        }

        if(!password.equals(repeatPassword))
        {
            throw new PasswordsDoNotMatchException();
        }

        if(this.userRepository.findByUsername(username).isPresent() || !this.userRepository.findByUsername(username).isEmpty())
        {
            throw new UserNameAlreadyExistsException(username);
        }
        User user=new User(username,password, name,  surname,  dateOfBirth,  weight);
        return this.userRepository.save(user);

    }

    @Override
    public void addWorkoutToUser(String username, Workout workout) {
        if(userRepository.findByUsername(username).isPresent())
        {
            User user=userRepository.findByUsername(username).get();
            user.getWorkoutsDone().add(workout);
            userRepository.save(user);
        }
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }
}
