package com.example.demo.service;

import com.example.demo.dto.PetDTO;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.*;

@Service
public class PetService {

    private final Map<Long, PetDTO> pets = new HashMap<>();
    private Long nextId = 1L;

    @PostConstruct
    public void init() {
        addDog("Rex", 4);
        addCat("Luna", 2);
        addDog("Buddy", 3);
        addCat("Molly", 1);
    }

    public List<PetDTO> getAllPets() {
        return new ArrayList<>(pets.values());
    }

    public List<PetDTO> getAvailablePets() {
        List<PetDTO> available = new ArrayList<>();
        for (PetDTO pet : pets.values()) {
            if (!pet.isAdopted()) available.add(pet);
        }
        return available;
    }

    public PetDTO getPetById(Long id) {
        return pets.get(id);
    }

    public PetDTO addDog(String name, int age) {
        PetDTO dog = new PetDTO(nextId++, name, age, false, "DOG");
        pets.put(dog.getId(), dog);
        return dog;
    }

    public PetDTO addCat(String name, int age) {
        PetDTO cat = new PetDTO(nextId++, name, age, false, "CAT");
        pets.put(cat.getId(), cat);
        return cat;
    }

    public String adoptPet(String adopterName, Long petId) {
        PetDTO pet = pets.get(petId);
        if (pet == null) return "Pet not found";
        if (pet.isAdopted()) return "Pet already adopted";
        pet.setAdopted(true);
        return adopterName + " adopted " + pet.getName();
    }

    public PetDTO updatePet(Long id, String name, int age) {
        PetDTO pet = pets.get(id);
        if (pet == null) return null;
        pet.setName(name);
        pet.setAge(age);
        return pet;
    }

    public boolean deletePet(Long id) {
        return pets.remove(id) != null;
    }

    // Для отладки
    public void printAllPets() {
        for (PetDTO pet : pets.values()) {
            System.out.println(pet.getId() + ": " + pet.getName() + " (" + pet.getType() + "), adopted: " + pet.isAdopted());
        }
    }
}
