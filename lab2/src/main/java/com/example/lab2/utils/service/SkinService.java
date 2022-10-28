package com.example.lab2.utils.service;

import com.example.lab2.utils.entity.Champion;
import com.example.lab2.utils.entity.Skin;
import com.example.lab2.utils.repository.SkinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SkinService {

    private SkinRepository repository;

    @Autowired
    public SkinService(SkinRepository repository) {
        this.repository = repository;
    }

    public Optional<Skin> find(String name) {
        return repository.findById(name);
    }

    public List<Skin> findAll() {
        return repository.findAll();
    }

    public List<Skin> findAll(Champion champion) {
        return repository.findByChampion(champion);
    }

    @Transactional
    public Skin create(Skin skin) {
        return repository.save(skin);
    }

    @Transactional
    public void delete(String name) {
        repository.deleteById(name);
    }

    @Transactional
    public void update(Skin skin){
        repository.save(skin);
    }
}
