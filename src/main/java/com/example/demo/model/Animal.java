package com.example.demo.model;

import lombok.Data;

@Data
public abstract class Animal {
    protected Long id;
    protected String name;
    protected int age;

    public abstract String makeSound();
    public abstract String getDescription();

    // Полиморфизм
    public String getAnimalInfo() {
        return "Name: " + name + ", Age: " + age + ", Sound: " + makeSound();
    }
}