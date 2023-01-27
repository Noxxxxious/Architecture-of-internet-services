package com.example.lab3_champion.utils.event;

import com.example.lab3_champion.utils.entity.Champion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ChampionEventRepository {
    private RestTemplate restTemplate;

    @Autowired
    public ChampionEventRepository(@Value("${skins.url}") String baseUrl) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Champion champion) {
        restTemplate.delete("/champions/{name}", champion.getName());
    }

    public void create(Champion champion) {
        restTemplate.postForLocation("/champions", PostChampionRequest.entityToDtoMapper().apply(champion));
    }
}
