package com.nikola.fitjourney.service.impl;

import com.nikola.fitjourney.model.Set;
import com.nikola.fitjourney.repository.jpa.SetRepository;
import com.nikola.fitjourney.service.SetService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SetServiceImpl implements SetService {

    private final SetRepository setRepository;

    public SetServiceImpl(SetRepository setRepository) {
        this.setRepository = setRepository;
    }

    @Override
    public Set save(int reps, Double weight) {
        return setRepository.save(new Set(reps,weight));
    }

    @Override
    public void deleteById(Long id) {
        this.setRepository.deleteById(id);
    }

    @Override
    public Optional<Set> findById(Long id) {
        return this.setRepository.findById(id);
    }
}
