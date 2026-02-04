package com.example.demo.dto;

import lombok.Data;

@Data
public class AdoptionRequestDTO {
    private String adopterName;
    private Long petId;
}