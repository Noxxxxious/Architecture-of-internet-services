package com.example.lab2.utils.dto;

import com.example.lab2.utils.entity.Champion;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
@EqualsAndHashCode
public class GetChampionResponse {
    private String name;
    private int priceBE;
    @ToString.Exclude
    private byte[] splashArt;

    public static Function<Champion, GetChampionResponse> entityToDtoMapper(){
        return champion ->GetChampionResponse.builder()
                .name(champion.getName())
                .priceBE(champion.getPriceBE())
                .splashArt(champion.getSplashArt())
                .build();
    }
}
