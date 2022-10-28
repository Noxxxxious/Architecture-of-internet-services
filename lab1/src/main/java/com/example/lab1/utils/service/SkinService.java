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

    public Optional<Skin> find(String address) {
        return repository.find(address);
    }

    public List<Skin> findAll() {
        return repository.findAll();
    }

    public void create(Skin skin) {
        repository.create(skin);
    }

    public void update(Skin skin) {
        repository.update(skin);
    }

    public void delete(String address) {
        repository.delete(repository.find(address).orElseThrow());
    }
}
