package com.example.lab3_skin.utils.service;

import com.example.lab3_skin.utils.entity.Champion;
import com.example.lab3_skin.utils.entity.Skin;
import com.example.lab3_skin.utils.repository.ChampionRepository;
import com.example.lab3_skin.utils.repository.SkinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SkinService {

    private SkinRepository skinRepository;
    private ChampionRepository championRepository;

    @Autowired
    public SkinService(SkinRepository skinRepository, ChampionRepository championRepository) {
        this.skinRepository = skinRepository;
        this.championRepository = championRepository;
    }

    public Optional<Skin> find(String name) {
        return skinRepository.findByName(name);
    }

    public Optional<Skin> find(String championName, String skinName){
        Optional<Champion> champion = championRepository.findById(championName);
        if (champion.isPresent()){
            return skinRepository.findByNameAndChampion(skinName, champion.get());
        }
        else {
            return Optional.empty();
        }
    }

    public List<Skin> findAll() {
        return skinRepository.findAll();
    }

    public List<Skin> findAll(Champion champion) {
        return skinRepository.findByChampion(champion);
    }

    @Transactional
    public Skin create(Skin skin) {
        return skinRepository.save(skin);
    }

    @Transactional
    public void delete(String name) {
        skinRepository.deleteById(name);
    }

    @Transactional
    public void update(Skin skin){
        skinRepository.save(skin);
    }
}
