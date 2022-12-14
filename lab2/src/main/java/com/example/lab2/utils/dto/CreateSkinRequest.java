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
public class CreateSkinRequest {
    private String name;
    private int priceRP;
    private Champion champion;
    @ToString.Exclude
    private byte[] splashArt;

    public static Function<CreateSkinRequest, Skin> dtoToEntityMapper(Function<String, Champion> championFunc){
        return request -> Skin.builder()
                .name(request.getName())
                .priceRP(request.getPriceRP())
                .champion(championFunc.apply(request.getChampion().getName()))
                .splashArt(request.getSplashArt())
                .build();
    }
}
