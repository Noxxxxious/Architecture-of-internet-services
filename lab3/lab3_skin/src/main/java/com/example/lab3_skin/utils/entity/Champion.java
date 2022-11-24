package com.example.lab3_skin.utils.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "champion")
    @ToString.Exclude
    private List<Skin> skins;
}
