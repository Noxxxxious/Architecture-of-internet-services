package com.example.lab1.utils.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
@EqualsAndHashCode
public class Skin implements Serializable {
    private String name;
    private int priceRP;
    private String tier;
    @ToString.Exclude
    private byte[] splashArt;
}
