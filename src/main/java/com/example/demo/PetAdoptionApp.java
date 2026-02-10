package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetAdoptionApp {
    public static void main(String[] args) {
        SpringApplication.run(PetAdoptionApp.class, args);
        System.out.println("🚀 Pet Adoption System запущен!");
        System.out.println("🌐 API: http://localhost:8080/api/pets");
        System.out.println("🖥️  Интерфейс: http://localhost:8080");
    }
}