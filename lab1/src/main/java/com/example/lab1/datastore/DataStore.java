package com.example.lab1.datastore;


import com.example.lab1.utils.entity.Champion;
import com.example.lab1.utils.entity.Skin;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Stream;

@Log
@Component
public class DataStore {
    private Set<Skin> skins = new HashSet<>();
    private Set<Champion> champions= new HashSet<>();

    public synchronized List<Skin> findAllSkins(){
        return new ArrayList<>(skins);
    }

    public synchronized List<Champion> findAllChampions(){
        return new ArrayList<>(champions);
    }

    public Stream<Skin> getSkinStream() {
        return skins.stream();
    }

    public Optional<Skin> findSkin(String name){
        return skins.stream()
                .filter(skin -> skin.getName().equals(name))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized List<Skin> findSkinsByChampion(String name){
        return skins.stream()
                .filter(skin -> skin.getChampion().getName().equals(name)).toList();
    }

    public Optional<Champion> findChampion(String name){
        return champions.stream()
                .filter(champion -> champion.getName().equals(name))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createSkin(Skin skin) throws IllegalArgumentException{
        findSkin(skin.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(String.format("The skin name \"%s\" is not unique", skin.getName()));
                },
                () -> skins.add(skin));
    }

    public synchronized void createChampion(Champion champion) throws IllegalArgumentException{
        findChampion(champion.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(String.format("The champion name \"%s\" is not unique", champion.getName()));
                },
                () -> champions.add(champion));
    }

    public synchronized void updateSkin(Skin skin) throws IllegalArgumentException{
        findSkin(skin.getName()).ifPresentOrElse(
                original -> {
                    skins.remove(original);
                    skins.add(skin);
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("Skin named \"%s\" does not exist", skin.getName()));
                });
    }

    public synchronized void deleteSkin(String name) throws IllegalArgumentException {
        findSkin(name).ifPresentOrElse(
                original -> skins.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("Skin named \"%s\" does not exist", name));
                });
    }

    public synchronized void deleteChampion(String name) throws IllegalArgumentException {
        findChampion(name).ifPresentOrElse(
                original -> champions.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("Champion named \"%s\" does not exist", name));
                });
    }


}
