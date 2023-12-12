package com.nikola.fitjourney.repository.jpa;

import com.nikola.fitjourney.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAnAndPassword(String password);
}
