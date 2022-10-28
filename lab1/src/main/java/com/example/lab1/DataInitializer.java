package com.example.lab1;

import com.example.lab1.utils.entity.Champion;
import com.example.lab1.utils.entity.Skin;
import com.example.lab1.utils.service.ChampionService;
import com.example.lab1.utils.service.SkinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {
    private SkinService skinService;
    private ChampionService championService;

    @Autowired
    public DataInitializer(SkinService skinService, ChampionService championService){
        this.skinService = skinService;
        this.championService = championService;
    }

    @PostConstruct
    private synchronized void init(){
        Champion vi = Champion.builder().name("Vi").priceBE(4800).priceRP(880).splashArt(null).build();
        Champion zac = Champion.builder().name("Zac").priceBE(4800).priceRP(880).splashArt(null).build();
        Champion taliyah = Champion.builder().name("Taliyah").priceBE(6300).priceRP(975).splashArt(null).build();
        Champion nidalee = Champion.builder().name("Nidalee").priceBE(3150).priceRP(790).splashArt(null).build();
        Champion gragas = Champion.builder().name("Gragas").priceBE(3150).priceRP(790).splashArt(null).build();
        Champion thresh = Champion.builder().name("Thresh").priceBE(4800).priceBE(880).splashArt(null).build();

        Skin vi_skin1 = Skin.builder().name("PsyOps Vi").priceRP(1350).tier("Epic").splashArt(null).build();
        Skin vi_skin2 = Skin.builder().name("Neon Strike Vi").priceRP(975).tier("Regular").splashArt(null).build();
        Skin zac_skin1 = Skin.builder().name("SKT T1 Zac ").priceRP(1350).tier("Legacy").splashArt(null).build();
        Skin taliyah_skin1 = Skin.builder().name("Freljord Taliyah").priceRP(1350).tier("Epic").splashArt(null).build();
        Skin nidalee_skin1 = Skin.builder().name("Leopard Nidalee").priceRP(520).tier("Regular").splashArt(null).build();
        Skin nidalee_skin2 = Skin.builder().name("Challenger Nidalee").priceRP(975).tier("Regular").splashArt(null).build();
        Skin gragas_skin1 = Skin.builder().name("Fnatic Gragas").priceRP(750).tier("Legacy").splashArt(null).build();
        Skin gragas_skin2 = Skin.builder().name("Scuba Gragas").priceRP(975).tier("Legacy").splashArt(null).build();
        Skin thresh_skin1 = Skin.builder().name("Dark Star Thresh").priceRP(1820).tier("Legendary").splashArt(null).build();
        Skin thresh_skin2 = Skin.builder().name("Pulsefire Thresh").priceRP(1820).tier("Mythic").splashArt(null).build();

        championService.create(vi);
        championService.create(zac);
        championService.create(taliyah);
        championService.create(nidalee);
        championService.create(gragas);
        championService.create(thresh);

        skinService.create(vi_skin1);
        skinService.create(vi_skin2);
        skinService.create(zac_skin1);
        skinService.create(taliyah_skin1);
        skinService.create(nidalee_skin1);
        skinService.create(nidalee_skin2);
        skinService.create(gragas_skin1);
        skinService.create(gragas_skin2);
        skinService.create(thresh_skin1);
        skinService.create(thresh_skin2);

    }
}
