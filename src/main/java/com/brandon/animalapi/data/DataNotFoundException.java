package com.brandon.animalapi.data;

public class DataNotFoundException extends RuntimeException {

    private String repository;
    private int index;

    public DataNotFoundException(String repository, int index) {
        super();
        this.repository = repository;
        this.index = index;
    }

    public DataNotFoundException(String repository) {
        super();
        this.repository = repository;
        this.index = -1;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
