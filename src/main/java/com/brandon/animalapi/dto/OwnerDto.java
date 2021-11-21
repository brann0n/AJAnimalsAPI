package com.brandon.animalapi.dto;

import javax.validation.constraints.*;

public class OwnerDto {
    private int id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    private String address;

    private boolean hasKids;

    @Positive
    private int familySize;

    public OwnerDto(){

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

    public boolean isHasKids() {
        return hasKids;
    }

    public void setHasKids(boolean hasKids) {
        this.hasKids = hasKids;
    }

    public int getFamilySize() {
        return familySize;
    }

    public void setFamilySize(int familySize) {
        this.familySize = familySize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
