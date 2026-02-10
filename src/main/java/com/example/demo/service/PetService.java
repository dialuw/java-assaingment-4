package com.example.demo.service;

import com.example.demo.dto.PetDTO;
import com.example.demo.dto.PetRequest;
import java.util.List;

public interface PetService {
    List<PetDTO> getAllPets();
    PetDTO getPetById(Long id);
    PetDTO createPet(PetRequest request);
    PetDTO updatePet(Long id, PetRequest request);
    void deletePet(Long id);
    String adoptPet(Long petId, String adopterName);
    int getAdoptedCount();
    List<PetDTO> getAvailablePets();
}