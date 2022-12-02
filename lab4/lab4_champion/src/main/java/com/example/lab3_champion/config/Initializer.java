package com.example.lab3_champion.config;

import com.example.lab3_champion.utils.entity.Champion;
import com.example.lab3_champion.utils.service.ChampionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Initializer {
    private final ChampionService championService;

    @Autowired
    public Initializer(ChampionService championService){
        this.championService = championService;
    }

    @PostConstruct
    private synchronized void init(){
        Champion vi = Champion.builder().name("Vi").priceBE(4800).splashArt(null).build();
        Champion zac = Champion.builder().name("Zac").priceBE(4800).splashArt(null).build();
        Champion taliyah = Champion.builder().name("Taliyah").priceBE(6300).splashArt(null).build();
        Champion nidalee = Champion.builder().name("Nidalee").priceBE(3150).splashArt(null).build();
        Champion gragas = Champion.builder().name("Gragas").priceBE(3150).splashArt(null).build();
        Champion thresh = Champion.builder().name("Thresh").priceBE(4800).splashArt(null).build();

        championService.create(vi);
        championService.create(zac);
        championService.create(taliyah);
        championService.create(nidalee);
        championService.create(gragas);
        championService.create(thresh);
    }
}
