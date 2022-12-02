package com.example.lab3_skin.utils.dto;

import com.example.lab3_skin.utils.entity.Skin;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
@EqualsAndHashCode
public class UpdateSkinRequest {
    private String name;
    private int priceRP;

    public static BiFunction<Skin, UpdateSkinRequest, Skin> dtoToEntityUpdater(){
        return (skin, request) -> {
            skin.setName(request.getName());
            skin.setPriceRP(request.getPriceRP());
            return skin;
        };
    }
}
