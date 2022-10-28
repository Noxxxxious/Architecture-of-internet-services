package com.example.lab2.utils.dto;

import com.example.lab2.utils.entity.Champion;
import com.example.lab2.utils.entity.Skin;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
@EqualsAndHashCode
public class GetSkinResponse {
    private String name;
    private int priceRP;
    private Champion champion;
    private byte[] splashArt;

    public static Function<Skin, GetSkinResponse> entityToDtoMapper() {
        return skin -> GetSkinResponse.builder()
                .name(skin.getName())
                .priceRP(skin.getPriceRP())
                .champion(skin.getChampion())
                .splashArt(skin.getSplashArt())
                .build();
    }
}
