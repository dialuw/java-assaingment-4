package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("DOG")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Dog extends Pet {

    public Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public String getPetType() {
        return "DOG";
    }
}