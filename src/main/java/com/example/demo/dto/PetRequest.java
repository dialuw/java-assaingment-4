package com.example.demo.dto;

import lombok.Data;

@Data
public class PetRequest {
    private String name;
    private int age;
    private String type; // DOG or CAT
    private String breed;
    private String color;
}