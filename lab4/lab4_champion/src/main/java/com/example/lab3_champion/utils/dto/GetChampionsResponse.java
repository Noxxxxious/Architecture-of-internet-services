package com.example.lab3_champion.utils.dto;

import com.example.lab3_champion.utils.entity.Champion;
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
public class GetChampionsResponse {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @Builder
    @EqualsAndHashCode
    public static class Champion {
        private String name;
        private int priceBE;
        @ToString.Exclude
        private byte[] splashArt;
    }

    @Singular
    private List<Champion> champions;

    public static Function<Collection<com.example.lab3_champion.utils.entity.Champion>, GetChampionsResponse> entityToDtoMapper(){
        return champions -> {
            GetChampionsResponseBuilder responseBuilder = GetChampionsResponse.builder();
            champions.stream().map(champion -> Champion.builder()
                    .name(champion.getName())
                    .priceBE(champion.getPriceBE())
                    .build())
                    .forEach(responseBuilder::champion);
            return responseBuilder.build();
        };
    }
}
