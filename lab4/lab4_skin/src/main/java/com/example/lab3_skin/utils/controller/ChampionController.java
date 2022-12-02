package com.example.lab3_skin.utils.controller;

import com.example.lab3_skin.utils.dto.*;
import com.example.lab3_skin.utils.entity.Champion;
import com.example.lab3_skin.utils.service.ChampionService;
import com.example.lab3_skin.utils.service.SkinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/champions")
public class ChampionController {

    private ChampionService championService;

    @Autowired
    public ChampionController(ChampionService championService) {
        this.championService = championService;
    }

    @PostMapping("")
    public ResponseEntity<Void> createChampion(@RequestBody CreateChampionRequest request, UriComponentsBuilder builder) {
        Champion champion = CreateChampionRequest
                .dtoToEntityMapper()
                .apply(request);
        champion = championService.create(champion);
        return ResponseEntity.created(builder.pathSegment("api", "champions", "{id}").buildAndExpand(champion.getName()).toUri()).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteChampion(@PathVariable("id") String id) {
        Optional<Champion> champion = championService.find(id);
        if (champion.isPresent()) {
            championService.delete(champion.get().getName());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
