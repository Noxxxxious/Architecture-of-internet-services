package com.example.lab1.utils.service;

import com.example.lab1.utils.entity.Champion;
import com.example.lab1.utils.repository.ChampionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChampionService {
    private ChampionRepository repository;

    @Autowired
    public ChampionService(ChampionRepository repository) {
        this.repository = repository;
    }

    public Optional<Champion> find(String name) {
        return repository.find(name);
    }

    public void create(Champion champion) {
        repository.create(champion);
    }
}
