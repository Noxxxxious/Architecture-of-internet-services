package com.example.lab2.utils.controller;

import com.example.lab2.utils.dto.*;
import com.example.lab2.utils.entity.Skin;
import com.example.lab2.utils.entity.Champion;
import com.example.lab2.utils.service.SkinService;
import com.example.lab2.utils.service.ChampionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@RestController
@RequestMapping("api/champions")
public class ChampionController {

    private SkinService skinService;
    private ChampionService championService;

    @Autowired
    public ChampionController(SkinService skinService, ChampionService championService) {
        this.skinService = skinService;
        this.championService = championService;
    }

    @GetMapping("{id}")
    public ResponseEntity<GetChampionResponse> getChampion(@PathVariable("id") String id) {
        Optional<Champion> champion = championService.find(id);
        return champion.map(value -> ResponseEntity.ok(GetChampionResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<GetChampionsResponse> getChampions() {
        return ResponseEntity.ok(GetChampionsResponse.entityToDtoMapper().apply(championService.findAll()));
    }

    @PostMapping
    public ResponseEntity<Void> createChampion(@RequestBody CreateChampionRequest request, UriComponentsBuilder builder) {
        Champion champion = CreateChampionRequest
                .dtoToEntityMapper()
                .apply(request);
        champion = championService.create(champion);
        return ResponseEntity.created(builder.pathSegment("api", "champions", "{id}").buildAndExpand(champion.getName()).toUri()).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateSkin(@RequestBody UpdateChampionRequest request, @PathVariable("id") String id) {
        Optional<Champion> champion = championService.find(id);
        if (champion.isPresent()) {
            UpdateChampionRequest.dtoToEntityUpdater().apply(champion.get(), request);
            championService.update(champion.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
