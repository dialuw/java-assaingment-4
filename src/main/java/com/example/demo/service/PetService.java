package com.example.demo.service;

import com.example.demo.dto.PetDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PetService {

    private final Map<Long, PetDTO> pets = new HashMap<>();
    private Long nextId = 1L;

    @PostConstruct
    public void init() {
        System.out.println("=== INITIALIZING PET SERVICE ===");

        // Добавляем тестовых питомцев
        addPet(new PetDTO(nextId++, "Rex", 4, false, "DOG"));
        addPet(new PetDTO(nextId++, "Luna", 2, false, "CAT"));
        addPet(new PetDTO(nextId++, "Buddy", 3, false, "DOG"));
        addPet(new PetDTO(nextId++, "Molly", 1, false, "CAT"));

        System.out.println("Initialized with " + pets.size() + " pets");
        System.out.println("=== PET SERVICE READY ===");
    }

    private void addPet(PetDTO pet) {
        pets.put(pet.getId(), pet);
        System.out.println("Added pet: " + pet.getName() + " (ID: " + pet.getId() + ")");
    }

    public List<PetDTO> getAllPets() {
        System.out.println("Getting all pets, count: " + pets.size());
        return new ArrayList<>(pets.values());
    }

    public List<PetDTO> getAvailablePets() {
        List<PetDTO> available = pets.values().stream()
                .filter(pet -> !pet.isAdopted())
                .toList();
        System.out.println("Getting available pets, count: " + available.size());
        return available;
    }

    public PetDTO getPetById(Long id) {
        System.out.println("Getting pet by ID: " + id);
        PetDTO pet = pets.get(id);
        if (pet == null) {
            System.out.println("Pet not found with ID: " + id);
        } else {
            System.out.println("Found pet: " + pet.getName());
        }
        return pet;
    }

    public PetDTO addDog(String name, int age) {
        System.out.println("=== ADDING NEW DOG ===");
        System.out.println("Name: " + name + ", Age: " + age);

        try {
            PetDTO newDog = new PetDTO(nextId++, name, age, false, "DOG");
            pets.put(newDog.getId(), newDog);

            System.out.println("Dog added successfully!");
            System.out.println("ID: " + newDog.getId());
            System.out.println("Name: " + newDog.getName());
            System.out.println("Age: " + newDog.getAge());
            System.out.println("=== DOG ADDED ===");

            return newDog;
        } catch (Exception e) {
            System.out.println("ERROR adding dog: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public PetDTO addCat(String name, int age) {
        System.out.println("=== ADDING NEW CAT ===");
        System.out.println("Name: " + name + ", Age: " + age);

        try {
            PetDTO newCat = new PetDTO(nextId++, name, age, false, "CAT");
            pets.put(newCat.getId(), newCat);

            System.out.println("Cat added successfully!");
            System.out.println("ID: " + newCat.getId());
            System.out.println("Name: " + newCat.getName());
            System.out.println("Age: " + newCat.getAge());
            System.out.println("=== CAT ADDED ===");

            return newCat;
        } catch (Exception e) {
            System.out.println("ERROR adding cat: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public String adoptPet(String adopterName, Long petId) {
        System.out.println("=== ADOPTION REQUEST ===");
        System.out.println("Adopter: " + adopterName);
        System.out.println("Pet ID: " + petId);

        PetDTO pet = pets.get(petId);

        if (pet == null) {
            System.out.println("ERROR: Pet not found with ID: " + petId);
            return "Pet not found with ID: " + petId;
        }

        if (pet.isAdopted()) {
            System.out.println("ERROR: Pet " + pet.getName() + " is already adopted");
            return "Pet " + pet.getName() + " is already adopted";
        }

        pet.setAdopted(true);
        System.out.println("SUCCESS: " + adopterName + " adopted " + pet.getName());
        System.out.println("=== ADOPTION COMPLETE ===");

        return adopterName + " successfully adopted " + pet.getName();
    }

    // Вспомогательный метод для отладки
    public void printAllPets() {
        System.out.println("=== CURRENT PETS IN SERVICE ===");
        pets.values().forEach(pet ->
                System.out.println(pet.getId() + ": " + pet.getName() +
                        " (" + pet.getType() + "), adopted: " + pet.isAdopted())
        );
        System.out.println("Total: " + pets.size() + " pets");
        System.out.println("================================");
    }
}