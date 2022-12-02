package com.example.lab3_champion.utils.service;

import com.example.lab3_champion.utils.entity.Champion;
import com.example.lab3_champion.utils.event.ChampionEventRepository;
import com.example.lab3_champion.utils.repository.ChampionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ChampionService {

    private ChampionRepository championRepository;
    private ChampionEventRepository eventRepository;

    @Autowired
    public ChampionService(ChampionRepository championRepository, ChampionEventRepository eventRepository){
        this.championRepository = championRepository;
        this.eventRepository = eventRepository;
    }

    public Optional<Champion> find(String name) {
        return championRepository.findById(name);
    }

    public List<Champion> findAll() {
        return championRepository.findAll();
    }

    @Transactional
    public Champion create(Champion champion) {
        Champion tempChampion = championRepository.save(champion);
        eventRepository.create(champion);
        return tempChampion;
    }

    @Transactional
    public void delete(String name) {
        Champion champion = championRepository.findById(name).get();
        championRepository.deleteById(name);
        eventRepository.delete(champion);
    }

    @Transactional
    public void update(Champion champion){
        championRepository.save(champion);
    }

}
