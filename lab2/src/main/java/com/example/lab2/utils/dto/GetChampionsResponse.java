package com.example.lab2.utils.dto;

import com.example.lab2.utils.entity.Champion;
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

    @Singular
    private List<Champion> champions;

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

    public static Function<Collection<com.example.lab2.utils.entity.Champion>, GetChampionsResponse> entityToDtoMapper(){
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
