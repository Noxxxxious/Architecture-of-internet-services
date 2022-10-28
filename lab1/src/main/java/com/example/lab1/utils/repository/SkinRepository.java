package com.example.lab1.utils.repository;

import com.example.lab1.datastore.DataStore;
import com.example.lab1.utils.entity.Skin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class SkinRepository implements com.example.lab1.utils.repository.Repository<Skin, String> {
    private DataStore store;

    @Autowired
    public SkinRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Skin> find(String name) {
        return store.findSkin(name);
    }

    @Override
    public List<Skin> findAll() {
        return store.findAllSkins();
    }

    public List<Skin> findByChampion(String name){
        return store.findSkinsByChampion(name);
    }

    @Override
    public void create(Skin entity) {
        store.createSkin(entity);
    }

    @Override
    public void delete(Skin entity) {
        store.deleteSkin(entity.getName());
    }

    @Override
    public void update(Skin entity) {
        store.updateSkin(entity);
    }
}
