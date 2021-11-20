package com.brandon.animalapi.models;

public class Owner implements IDataModel {

    private String name;
    private String address;
    private boolean hasKids;
    private int familySize;

    public Owner(String name, String address, boolean hasKids, int familySize) {
        this.name = name;
        this.address = address;
        this.hasKids = hasKids;
        this.familySize = familySize;
    }

    public Owner() {

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
}
