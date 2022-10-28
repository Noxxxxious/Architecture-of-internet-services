package com.example.lab1.utils.repository;

import com.example.lab1.datastore.DataStore;
import com.example.lab1.utils.entity.Champion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class ChampionRepository implements com.example.lab1.utils.repository.Repository<Champion, String> {

    private DataStore store;

    @Autowired
    public ChampionRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Champion> find(String name) {
        return store.findChampion(name);
    }

    @Override
    public List<Champion> findAll() {
        return store.findAllChampions();
    }

    @Override
    public void create(Champion entity) {
        store.createChampion(entity);
    }

    @Override
    public void delete(Champion entity) {
        throw new UnsupportedOperationException("Operation not implemented.");
    }

    @Override
    public void update(Champion entity) {
        throw new UnsupportedOperationException("Operation not implemented.");
    }
}
