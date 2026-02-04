package com.example.demo.controller;

import com.example.demo.dto.CreatePetRequest;
import com.example.demo.dto.PetDTO;
import com.example.demo.dto.AdoptRequest;
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

    // ===== GET =====
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

    // ===== POST =====
    @PostMapping("/dogs")
    public ResponseEntity<PetDTO> addDog(@RequestBody CreatePetRequest request) {
        PetDTO dog = petService.addDog(request.getName(), request.getAge());
        return ResponseEntity.ok(dog);
    }

    @PostMapping("/cats")
    public ResponseEntity<PetDTO> addCat(@RequestBody CreatePetRequest request) {
        PetDTO cat = petService.addCat(request.getName(), request.getAge());
        return ResponseEntity.ok(cat);
    }

    @PostMapping("/adopt")
    public ResponseEntity<String> adoptPet(@RequestBody AdoptRequest request) {
        String result = petService.adoptPet(request.getAdopterName(), request.getPetId());
        return ResponseEntity.ok(result);
    }

    // ===== PUT =====
    @PutMapping("/{id}")
    public ResponseEntity<PetDTO> updatePet(@PathVariable Long id, @RequestBody CreatePetRequest request) {
        PetDTO updated = petService.updatePet(id, request.getName(), request.getAge());
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // ===== DELETE =====
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePet(@PathVariable Long id) {
        boolean deleted = petService.deletePet(id);
        return deleted ? ResponseEntity.ok("Pet deleted") : ResponseEntity.notFound().build();
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
