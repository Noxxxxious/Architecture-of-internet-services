package com.example.lab2.utils.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "champions")
public class Champion implements Serializable {
    @Id
    private String name;
    @Column
    private int priceBE;
    @Column
    @ToString.Exclude
    private byte[] splashArt;
}
