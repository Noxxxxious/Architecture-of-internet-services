package com.example.lab2.utils.service;

import com.example.lab2.utils.entity.Champion;
import com.example.lab2.utils.repository.ChampionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChampionService {
    private ChampionRepository repository;

    @Autowired
    public ChampionService(ChampionRepository repository) {
        this.repository = repository;
    }

    public List<Champion> findAll() {
        return repository.findAll();
    }

    public Optional<Champion> find(String name) {
        return repository.find(name);
    }

    public Champion create(Champion champion) {
        repository.create(champion);
    }

    public void delete(String name) {
        repository.delete(repository.find(name).orElseThrow());
    }
}
