package com.example.lab3_skin.utils.repository;

import com.example.lab3_skin.utils.entity.Champion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionRepository extends JpaRepository<Champion, String> {

}
