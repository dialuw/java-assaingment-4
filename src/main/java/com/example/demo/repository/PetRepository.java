package com.example.demo.repository;

import com.example.demo.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByAdoptedFalse();

    @Query("SELECT COUNT(p) FROM Pet p WHERE p.adopted = true")
    int countAdopted();
}