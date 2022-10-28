package com.example.lab1.utils.service;

import com.example.lab1.utils.entity.Skin;
import com.example.lab1.utils.repository.SkinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return repository.find(name);
    }

    public List<Skin> findAll() {
        return repository.findAll();
    }

    public List<Skin> findByChampion(String name) {
        return repository.findByChampion(name);
    }

    public void create(Skin skin) {
        repository.create(skin);
    }

    public void update(Skin skin) {
        repository.update(skin);
    }

    public void delete(String name) {
        repository.delete(repository.find(name).orElseThrow());
    }
}
