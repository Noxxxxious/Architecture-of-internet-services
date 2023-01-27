package com.example.lab3_champion.utils.dto;

import com.example.lab3_champion.utils.entity.Champion;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
@EqualsAndHashCode
public class UpdateChampionRequest {
    private String name;
    private int priceBE;
    private byte[] splashArt;

    public static BiFunction<Champion, UpdateChampionRequest, Champion> dtoToEntityUpdater(){
        return (champion, request) ->{
            champion.setName(request.getName());
            champion.setPriceBE(request.getPriceBE());
            champion.setSplashArt(request.getSplashArt());
            return champion;
        };
    }
}
