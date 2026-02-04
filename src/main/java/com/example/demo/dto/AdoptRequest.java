package com.example.demo.dto;

import lombok.Data;

@Data
public class AdoptRequest {
    private String adopterName;
    private Long petId;
}