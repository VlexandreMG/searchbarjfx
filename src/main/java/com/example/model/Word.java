package com.example.model;

public class Word {
    private String name;

    public Word(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Word() {
        
    }

    @Override
    public String toString() {
        return name;
    }
}