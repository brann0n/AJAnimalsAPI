package com.brandon.animalapi.dto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

public class AnimalDto {

    @Positive
    private int index;

    @Size(max = 50)
    private String name;

    @Size(max = 20)
    private String type;

    @PositiveOrZero
    private int age;

    public AnimalDto(){

    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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
}
