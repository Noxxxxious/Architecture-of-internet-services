package com.example.lab3_skin.utils.repository;

import com.example.lab3_skin.utils.entity.Champion;
import com.example.lab3_skin.utils.entity.Skin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface SkinRepository extends JpaRepository<Skin, String> {
    List<Skin> findByChampion(Champion champion);
    Optional<Skin> findByNameAndChampion(String name, Champion champion);
}
