package com.example.lab1.champion;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Champion {
    private String name;
    private String title;
    private int price;
    private List<Skin> skins;
}
