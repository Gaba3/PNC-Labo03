package com.example.labo03.services.impl;

import com.example.labo03.dto.request.CreateSpecimenRequest;
import com.example.labo03.dto.request.UpdateSpecimenRequest;
import com.example.labo03.dto.response.SpecimenResponse;
import com.example.labo03.entities.Specimen;
import com.example.labo03.exceptions.ResourceNotFoundException;
import com.example.labo03.mappers.SpecimenMapper;
import com.example.labo03.repositories.SpecimenRepository;
import com.example.labo03.services.SpecimenService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.labo03.dto.PageableResponse;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpecimenServiceImpl implements SpecimenService {
    private final SpecimenRepository specimenRepository;
    private final SpecimenMapper specimenMapper;

    @Override
    @Transactional
    public SpecimenResponse createSpecimen(CreateSpecimenRequest request) {
        return specimenMapper.toDto(
                specimenRepository.save(specimenMapper.toEntity(request))
        );
    }

    @Override
    public PageableResponse getAllSpecimens(Pageable pageable) {
        Page<Specimen> specimenPage = specimenRepository.findAll(pageable);
        if (specimenPage.isEmpty()) {
            throw new ResourceNotFoundException("No specimens are registered in Hyrule");
        }
        return specimenMapper.pageToDto(specimenPage);
    }

    @Override
    public SpecimenResponse getSpecimenById(UUID id) {
        return specimenMapper.toDto(specimenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specimen not found in Hyrule Records"))
        );
    }

    @Override
    @Transactional
    public SpecimenResponse updateSpecimen(UUID id, UpdateSpecimenRequest request) {
        this.getSpecimenById(id);
        return specimenMapper.toDto(specimenRepository.save(specimenMapper.toEntityUpdate(request, id)));
    }

    @Transactional
    public SpecimenResponse deleteSpecimen(UUID id) {
        SpecimenResponse existSpecimen = this.getSpecimenById(id);
        specimenRepository.deleteById(id);
        return existSpecimen;
    }
}