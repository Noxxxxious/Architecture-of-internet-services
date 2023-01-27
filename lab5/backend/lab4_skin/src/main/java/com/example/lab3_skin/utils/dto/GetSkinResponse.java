package com.example.lab3_skin.utils.dto;

import com.example.lab3_skin.utils.entity.Champion;
import com.example.lab3_skin.utils.entity.Skin;
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
    private String champion;
    private byte[] splashArt;

    public static Function<Skin, GetSkinResponse> entityToDtoMapper() {
        return skin -> GetSkinResponse.builder()
                .name(skin.getName())
                .priceRP(skin.getPriceRP())
                .champion(skin.getChampion().getName())
                .splashArt(skin.getSplashArt())
                .build();
    }
}
