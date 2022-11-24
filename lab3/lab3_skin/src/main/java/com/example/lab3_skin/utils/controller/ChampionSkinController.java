package com.example.lab3_skin.utils.controller;


import com.example.lab3_skin.utils.dto.CreateSkinRequest;
import com.example.lab3_skin.utils.dto.GetSkinResponse;
import com.example.lab3_skin.utils.dto.GetSkinsResponse;
import com.example.lab3_skin.utils.dto.UpdateSkinRequest;
import com.example.lab3_skin.utils.entity.Champion;
import com.example.lab3_skin.utils.entity.Skin;
import com.example.lab3_skin.utils.service.ChampionService;
import com.example.lab3_skin.utils.service.SkinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/champions/{champion}/skins")
public class ChampionSkinController {
    private SkinService skinService;
    private ChampionService championService;

    @Autowired
    public ChampionSkinController(SkinService skinService, ChampionService championService) {
        this.championService = championService;
        this.skinService = skinService;
    }

    @GetMapping
    public ResponseEntity<GetSkinsResponse> getSkins(@PathVariable("champion") String name) {
        Optional<Champion> champion = championService.find(name);
        return champion.map(value -> ResponseEntity.ok(GetSkinsResponse.entityToDtoMapper().apply(skinService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetSkinResponse> getSkin(@PathVariable("champion") String name,
                                                   @PathVariable("id") String id) {
        return skinService.find(name)
                .map(value -> ResponseEntity.ok(GetSkinResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> postSkin(@PathVariable("champion") String name,
                                         @RequestBody CreateSkinRequest request,
                                         UriComponentsBuilder builder) {
        Optional<Champion> champion = championService.find(name);
        if (champion.isPresent()) {
            Skin skin = CreateSkinRequest.dtoToEntityMapper(n -> championService.find(n).orElseThrow()).apply(request);
            skin = skinService.create(skin);
            return ResponseEntity.created(builder.pathSegment("api","champions","{champion}","skins","{id}")
                    .buildAndExpand(champion.get().getName()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSkin(@PathVariable("champion") String name,
                                           @PathVariable("id") String address) {
        Optional<Skin> skin = skinService.find(name);
        if (skin.isPresent()) {
            skinService.delete(skin.get().getName());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> putSkin(@PathVariable("champion") String name, @RequestBody UpdateSkinRequest request) {
        Optional<Skin> skin = skinService.find(name);
        if (skin.isPresent()) {
            UpdateSkinRequest.dtoToEntityUpdater().apply(skin.get(), request);
            skinService.update(skin.get());
            return  ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
