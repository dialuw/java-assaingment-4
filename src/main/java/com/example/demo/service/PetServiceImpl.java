package com.example.demo.service;

import com.example.demo.dto.PetDTO;
import com.example.demo.dto.PetRequest;
import com.example.demo.model.*;
import com.example.demo.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PetDTO> getAllPets() {
        log.info("Getting all pets");
        return petRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PetDTO getPetById(Long id) {
        log.info("Getting pet by id: {}", id);
        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));
        return toDTO(pet);
    }

    @Override
    @Transactional
    public PetDTO createPet(PetRequest request) {
        log.info("Creating new pet: {}", request.getName());

        Pet pet;
        if ("DOG".equals(request.getType())) {
            Dog dog = new Dog();
            dog.setBreed(request.getBreed());
            pet = dog;
        } else {
            Cat cat = new Cat();
            cat.setColor(request.getColor());
            pet = cat;
        }

        pet.setName(request.getName());
        pet.setAge(request.getAge());

        Pet saved = petRepository.save(pet);
        log.info("Pet created with id: {}", saved.getId());

        return toDTO(saved);
    }

    @Override
    @Transactional
    public PetDTO updatePet(Long id, PetRequest request) {
        log.info("Updating pet: {}", id);

        Pet pet = petRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        pet.setName(request.getName());
        pet.setAge(request.getAge());

        if (pet instanceof Dog && request.getBreed() != null) {
            ((Dog) pet).setBreed(request.getBreed());
        } else if (pet instanceof Cat && request.getColor() != null) {
            ((Cat) pet).setColor(request.getColor());
        }

        return toDTO(petRepository.save(pet));
    }

    @Override
    @Transactional
    public void deletePet(Long id) {
        log.info("Deleting pet: {}", id);
        petRepository.deleteById(id);
    }

    @Override
    @Transactional
    public String adoptPet(Long petId, String adopterName) {
        log.info("Adopting pet {} by {}", petId, adopterName);

        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        if (pet.isAdopted()) {
            throw new RuntimeException("Pet already adopted");
        }

        pet.setAdopted(true);
        petRepository.save(pet);

        return adopterName + " adopted " + pet.getName() + "! 🎉";
    }

    @Override
    @Transactional(readOnly = true)
    public int getAdoptedCount() {
        return petRepository.countAdopted();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PetDTO> getAvailablePets() {
        return petRepository.findByAdoptedFalse().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private PetDTO toDTO(Pet pet) {
        PetDTO dto = new PetDTO();
        dto.setId(pet.getId());
        dto.setName(pet.getName());
        dto.setAge(pet.getAge());
        dto.setAdopted(pet.isAdopted());
        dto.setType(pet.getType());
        dto.setSound(pet.getSound());
        dto.setInfo(pet.getInfo());

        if (pet instanceof Dog) {
            dto.setBreed(((Dog) pet).getBreed());
        } else if (pet instanceof Cat) {
            dto.setColor(((Cat) pet).getColor());
        }

        return dto;
    }
}