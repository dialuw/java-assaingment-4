package com.example.demo.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("CAT")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Cat extends Pet {

    public Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public String getPetType() {
        return "CAT";
    }
}