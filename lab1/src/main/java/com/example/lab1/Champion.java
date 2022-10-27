package com.example.lab1;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Champion implements Serializable {
    private String name;
    private String title;
    private int priceRP;
    private int priceBE;
    private List<Skin> skins;
}