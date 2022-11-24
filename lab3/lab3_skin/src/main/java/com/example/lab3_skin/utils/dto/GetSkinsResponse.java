package com.example.lab3_skin.utils.dto;

import com.example.lab3_skin.utils.entity.Skin;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
@EqualsAndHashCode
public class GetSkinsResponse {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @Builder
    @EqualsAndHashCode
    public static class Skin {
        private String name;
        private int priceRP;
    }

    @Singular
    private List<Skin> skins;

    public static Function<Collection<com.example.lab3_skin.utils.entity.Skin>, GetSkinsResponse> entityToDtoMapper(){
        return skins -> {
            GetSkinsResponseBuilder responseBuilder = GetSkinsResponse.builder();
            skins.stream().map(skin -> Skin.builder()
                    .name(skin.getName())
                    .priceRP(skin.getPriceRP())
                    .build())
                    .forEach(responseBuilder::skin);
            return responseBuilder.build();
        };
    }
}
