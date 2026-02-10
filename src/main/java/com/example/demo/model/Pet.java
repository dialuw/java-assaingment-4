package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pets")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "pet_type")
@Data
public abstract class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int age;
    private boolean adopted = false;

    // Полиморфизм
    public abstract String getSound();
    public abstract String getType();

    public String getInfo() {
        return name + " (" + getType() + "), Age: " + age + ", Sound: " + getSound();
    }
}