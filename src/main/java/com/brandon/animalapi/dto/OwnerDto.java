package com.brandon.animalapi.dto;

import javax.validation.constraints.*;

public class OwnerDto {
    private long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    private String address;

    @Positive
    private int familySize;

    public OwnerDto() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFamilySize() {
        return familySize;
    }

    public void setFamilySize(int familySize) {
        this.familySize = familySize;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
