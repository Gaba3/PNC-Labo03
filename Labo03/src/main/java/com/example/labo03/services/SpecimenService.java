package com.example.labo03.services;

import com.example.labo03.dto.request.CreateSpecimenRequest;
import com.example.labo03.dto.request.UpdateSpecimenRequest;
import com.example.labo03.dto.response.SpecimenResponse;
import org.springframework.data.domain.Pageable;
import com.example.labo03.dto.PageableResponse;
import java.util.UUID;

public interface SpecimenService {
    SpecimenResponse createSpecimen(CreateSpecimenRequest request);
    PageableResponse getAllSpecimens(Pageable pageable);
    SpecimenResponse getSpecimenById(UUID id);
    SpecimenResponse updateSpecimen(UUID id, UpdateSpecimenRequest request);
    SpecimenResponse deleteSpecimen(UUID id);
}