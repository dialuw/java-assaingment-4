package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("DOG")
@Data
@EqualsAndHashCode(callSuper = true)
public class Dog extends Pet {
    private String breed;

    @Override
    public String getSound() {
        return "Woof!";
    }

    @Override
    public String getType() {
        return "Dog";
    }
}