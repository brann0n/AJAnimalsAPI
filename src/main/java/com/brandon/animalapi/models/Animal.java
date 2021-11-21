package com.brandon.animalapi.models;


public class Animal implements IDataModel {

    private int id;
    private String name;
    private String type;
    private int age;
    private int ownerId;

    public Animal(String name, String type, int age, int ownerId) {
        this.name = name;
        this.type = type;
        this.age = age;
        this.ownerId = ownerId;
    }

    public Animal(){

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}
