package com.brandon.animalapi.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "animal")
@Entity
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter @Setter private Long id;

    @Column(name = "name", nullable = false, length = 50)
    @Getter @Setter private String name;

    @Column(name = "type", nullable = false, length = 20)
    @Getter @Setter private String type;

    @Column(name = "age", nullable = false)
    @Getter @Setter private Integer age;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    @Getter @Setter private Owner owner;

    public Animal(String name, String type, int age){
        this.name = name;
        this.type = type;
        this.age = age;
    }

    public Animal(){

    }
}