package com.example.lab3_champion.utils.repository;

import com.example.lab3_champion.utils.entity.Champion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionRepository extends JpaRepository<Champion, String> {

}
