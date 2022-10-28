package com.example.lab2.utils.repository;

import com.example.lab2.utils.entity.Champion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionRepository extends JpaRepository<Champion, String> {

}
