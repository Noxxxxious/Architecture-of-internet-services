package com.example.lab2.utils.repository;

import com.example.lab2.utils.entity.Skin;
import com.example.lab2.utils.entity.Champion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkinRepository extends JpaRepository<Skin, String> {
    List<Skin> findByChampion(Champion champion);
}
