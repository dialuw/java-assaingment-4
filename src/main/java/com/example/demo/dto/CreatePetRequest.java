package com.example.demo.dto;

import lombok.Data;

@Data
public class CreatePetRequest {
    private String name;
    private int age;
}
