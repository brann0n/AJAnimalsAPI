package com.brandon.animalapi.data;

public class DataNotFoundException extends RuntimeException {

    private String key;
    private int index;

    public DataNotFoundException(String key, int index) {
        super();
        this.key = key;
        this.index = index;
    }

    public DataNotFoundException(String key) {
        super();
        this.key = key;
        this.index = -1;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
