package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("CAT")
@Data
@EqualsAndHashCode(callSuper = true)
public class Cat extends Pet {
    private String color;

    @Override
    public String getSound() {
        return "Meow!";
    }

    @Override
    public String getType() {
        return "Cat";
    }
}