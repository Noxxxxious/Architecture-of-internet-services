package com.example.lab2.utils.controller;

import com.example.lab2.utils.dto.CreateSkinRequest;
import com.example.lab2.utils.dto.GetSkinResponse;
import com.example.lab2.utils.dto.GetSkinsResponse;
import com.example.lab2.utils.dto.UpdateSkinRequest;
import com.example.lab2.utils.entity.Skin;
import com.example.lab2.utils.service.SkinService;
import com.example.lab2.utils.service.ChampionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@RestController
@RequestMapping("api/skins")
public class SkinController {
    private SkinService skinService;
    private ChampionService championService;

    @Autowired
    public SkinController(SkinService skinService, ChampionService championService) {
        this.skinService = skinService;
        this.championService = championService;
    }

    @GetMapping("{id}")
    public ResponseEntity<GetSkinResponse> getSkin(@PathVariable("id") String id) {
        Optional<Skin> skin = skinService.find(id);
        return skin
                .map(value -> ResponseEntity
                        .ok(GetSkinResponse
                                .entityToDtoMapper()
                                .apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());
    }

    @GetMapping
    public ResponseEntity<GetSkinsResponse> getSkins() {
        return ResponseEntity.ok(GetSkinsResponse
                        .entityToDtoMapper()
                        .apply(skinService.findAll()));
    }

    @PostMapping
    public ResponseEntity<Void> createSkin(@RequestBody CreateSkinRequest request, UriComponentsBuilder builder) {
        Skin skin = CreateSkinRequest
                .dtoToEntityMapper(name -> championService
                        .find(name)
                        .orElseThrow())
                .apply(request);
        skin = skinService.create(skin);
        return ResponseEntity.created(builder
                .pathSegment("api", "skins", "{id}")
                .buildAndExpand(skin.getName()).toUri()).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateSkin(@RequestBody UpdateSkinRequest request, @PathVariable("id") String id) {
        Optional<Skin> skin = skinService.find(id);
        if (skin.isPresent()) {
            UpdateSkinRequest
                    .dtoToEntityUpdater()
                    .apply(skin.get(), request);
            skinService.update(skin.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSkin(@PathVariable("id") String id) {
        Optional<Skin> skin = skinService.find(id);
        if (skin.isPresent()) {
            skinService.delete(skin.get().getName());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

