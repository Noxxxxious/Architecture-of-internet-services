package com.example.lab3_champion.utils.event;

import com.example.lab3_champion.utils.entity.Champion;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PostChampionRequest {

    private String name;

    public static Function<Champion, PostChampionRequest> entityToDtoMapper() {
        return entity -> PostChampionRequest.builder()
                .name(entity.getName())
                .build();
    }
}
