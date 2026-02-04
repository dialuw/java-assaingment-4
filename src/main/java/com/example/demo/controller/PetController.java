package com.example.demo.controller;

import com.example.demo.dto.PetDTO;
import com.example.demo.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public ResponseEntity<List<PetDTO>> getAllPets() {
        return ResponseEntity.ok(petService.getAllPets());
    }

    @GetMapping("/available")
    public ResponseEntity<List<PetDTO>> getAvailablePets() {
        return ResponseEntity.ok(petService.getAvailablePets());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDTO> getPetById(@PathVariable Long id) {
        PetDTO pet = petService.getPetById(id);
        return pet != null ? ResponseEntity.ok(pet) : ResponseEntity.notFound().build();
    }

    @PostMapping("/dogs")
    public ResponseEntity<PetDTO> addDog(
            @RequestParam String name,
            @RequestParam int age) {
        PetDTO dog = petService.addDog(name, age);
        return ResponseEntity.ok(dog);
    }

    @PostMapping("/cats")
    public ResponseEntity<PetDTO> addCat(
            @RequestParam String name,
            @RequestParam int age) {
        PetDTO cat = petService.addCat(name, age);
        return ResponseEntity.ok(cat);
    }

    @PostMapping("/adopt")
    public ResponseEntity<String> adoptPet(
            @RequestParam String adopterName,
            @RequestParam Long petId) {
        String result = petService.adoptPet(adopterName, petId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("REST API is working!");
    }

    @GetMapping("/debug")
    public ResponseEntity<String> debug() {
        petService.printAllPets();
        return ResponseEntity.ok("Debug info printed to console");
    }
}
