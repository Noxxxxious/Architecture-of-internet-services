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
public class Champion implements Serializable {
    private String name;
    private String title;
    private int priceRP;
    private int priceBE;
    @ToString.Exclude
    private byte[] splashArt;
}