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
public class CreateSkinRequest {
    private String name;
    private int priceRP;
    @ToString.Exclude
    private byte[] splashArt;
    private String champion;

    public static Function<CreateSkinRequest, Skin> dtoToEntityMapper(Function<String, Champion> championFunc){
        return request -> Skin.builder()
                .name(request.getName())
                .priceRP(request.getPriceRP())
                .champion(championFunc.apply(request.getChampion()))
                .splashArt(request.getSplashArt())
                .build();
    }
}
