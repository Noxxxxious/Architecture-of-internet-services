package com.example.lab3_skin.utils.service;

import com.example.lab3_skin.utils.entity.Champion;
import com.example.lab3_skin.utils.repository.ChampionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ChampionService {

    private ChampionRepository championRepository;

    @Autowired
    public ChampionService(ChampionRepository repository) {
        this.championRepository = repository;
    }

    public Optional<Champion> find(String name) {
        return championRepository.findById(name);
    }

    @Transactional
    public Champion create(Champion champion) {
        return championRepository.save(champion);
    }

    @Transactional
    public void delete(String name) {
        championRepository.deleteById(name);
    }

}
