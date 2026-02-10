package com.example.demo.controller;

import com.example.demo.dto.PetDTO;
import com.example.demo.dto.PetRequest;
import com.example.demo.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pets")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class PetController {

    private final PetService petService;

    @GetMapping
    public List<PetDTO> getAll() {
        return petService.getAllPets();
    }

    @GetMapping("/available")
    public List<PetDTO> getAvailable() {
        return petService.getAvailablePets();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDTO> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(petService.getPetById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public PetDTO create(@RequestBody PetRequest request) {
        return petService.createPet(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetDTO> update(@PathVariable Long id,
                                         @RequestBody PetRequest request) {
        try {
            return ResponseEntity.ok(petService.updatePet(id, request));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            petService.deletePet(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/adopt")
    public ResponseEntity<String> adopt(@PathVariable Long id,
                                        @RequestBody Map<String, String> request) {
        try {
            String adopterName = request.get("adopterName");
            return ResponseEntity.ok(petService.adoptPet(id, adopterName));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/stats")
    public Map<String, Object> getStats() {
        List<PetDTO> all = petService.getAllPets();
        int adopted = petService.getAdoptedCount();

        return Map.of(
                "total", all.size(),
                "adopted", adopted,
                "available", all.size() - adopted
        );
    }

    @GetMapping("/health")
    public String health() {
        return "✅ Pet Adoption API is running!";
    }
}