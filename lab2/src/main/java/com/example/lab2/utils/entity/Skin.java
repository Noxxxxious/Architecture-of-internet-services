package com.example.lab2.utils.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "skins")
public class Skin implements Serializable {
    @Id
    private String name;
    @Column
    private int priceRP;
    @ManyToOne
    @JoinColumn(name = "CHAMPIONS")
    private Champion champion;
    @Column
    @ToString.Exclude
    private byte[] splashArt;
}
