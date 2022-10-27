package com.example.lab1;


import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import com.example.lab1.serialization.CloningUtility;

import java.util.*;
import java.util.stream.Stream;

@Log
@Component
public class DataStore {
    private Set<Skin> skins = new HashSet<>();
    private Set<Champion> champions= new HashSet<>();

    public synchronized List<Champion> findAllChampions(){
        return new ArrayList<>(champions);
    }

    public Stream<Champion> getChampionStream() {
        return champions.stream();
    }

    public Optional<Champion> findChampion(String name){
        return champions.stream()
                .filter(champion -> champion.getName().equals(name))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createChampion(Champion champion) throws IllegalArgumentException{
        findChampion(champion.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(String.format("The champion name \"%s\" is not unique", champion.getName()));
                },
                () -> champions.add(champion));
    }

    public synchronized void updateChampion(Champion champion) throws IllegalArgumentException{
        findChampion(champion.getName()).ifPresentOrElse(
                original -> {
                    champions.remove(original);
                    champions.add(champion);
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("Champion named \"%s\" does not exist", champion.getName()));
                });
    }

    public synchronized void deleteCharacter(String name) throws IllegalArgumentException {
        findChampion(name).ifPresentOrElse(
                original -> champions.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("Champion named \"%s\" does not exist", name));
                });
    }
}
