package com.nikola.fitjourney.service;

import com.nikola.fitjourney.model.Set;

import java.util.Optional;

public interface SetService {

    Set save(int reps, Double weight);
    void deleteById(Long id);
    Optional<Set> findById(Long id);


}
