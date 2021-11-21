package com.brandon.animalapi.dto;

import javax.validation.constraints.*;

public class AnimalDto {
    private int id;

    @Size(max = 50)
    @NotBlank
    private String name;

    @Size(max = 20)
    @NotBlank
    private String type;

    @PositiveOrZero
    private int age;

    @Positive
    @NotNull
    private int ownerId;

    public AnimalDto() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
