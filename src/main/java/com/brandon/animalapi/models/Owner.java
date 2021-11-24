package com.brandon.animalapi.models;

import javax.persistence.*;

@Table(name = "owner")
@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "familiy_size", nullable = false)
    private Integer familiySize;

    public Owner(String name, String address, int familiySize){
        this.name = name;
        this.address = address;
        this.familiySize = familiySize;
    }

    public Owner(){

    }

    public Integer getFamiliySize() {
        return familiySize;
    }

    public void setFamiliySize(Integer familiySize) {
        this.familiySize = familiySize;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}