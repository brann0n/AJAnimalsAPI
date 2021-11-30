package com.brandon.animalapi.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "owner")
@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter @Setter private Long id;

    @Column(name = "name", nullable = false, length = 50)
    @Getter @Setter private String name;

    @Column(name = "address", nullable = false, length = 100)
    @Getter @Setter private String address;

    @Column(name = "familiy_size", nullable = false)
    @Getter @Setter private Integer familiySize;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    @Getter @Setter private List<Animal> animalList = new ArrayList<>(0);

    public Owner(String name, String address, int familiySize){
        this.name = name;
        this.address = address;
        this.familiySize = familiySize;
    }

    public Owner(){

    }


}