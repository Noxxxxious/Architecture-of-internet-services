package com.example.lab2.utils.service;

import com.example.lab2.utils.entity.Skin;
import com.example.lab2.utils.repository.SkinRepository;
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
        return repository.findById(name);
    }

    public List<Skin> findAll() {
        return repository.findAll();
    }

    public List<Skin> findByChampion(String name) {
        return repository.findByChampion(name);
    }

    public Skin create(Skin skin) {
        repository.save(skin);
    }

    public void delete(String name) {
        repository.deleteById(name);
    }
}
