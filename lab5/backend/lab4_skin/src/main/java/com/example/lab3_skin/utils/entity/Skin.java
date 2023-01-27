package com.example.lab3_skin.utils.entity;

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
    @Column
    @ToString.Exclude
    private byte[] splashArt;

    @ManyToOne
    @JoinColumn(name = "champion")
    private Champion champion;
}
