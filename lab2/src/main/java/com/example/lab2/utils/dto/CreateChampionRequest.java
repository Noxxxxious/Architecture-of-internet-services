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
public class CreateChampionRequest {
    private String name;
    private int priceBE;
    @ToString.Exclude
    private byte[] splashArt;

    public static Function<CreateChampionRequest, Champion> dtoToEntityMapper() {
        return request -> Champion.builder()
                .name(request.getName())
                .priceBE(request.getPriceBE())
                .splashArt(request.getSplashArt())
                .build();
    }
}
