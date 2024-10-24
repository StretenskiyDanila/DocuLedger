package org.example.businesspack.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.businesspack.dto.MasterDto;
import org.example.businesspack.entities.Master;
import org.example.businesspack.repositories.MasterRepository;
import org.example.businesspack.services.BusinessPackService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessPackServiceImpl implements BusinessPackService {

    private final MasterRepository masterRepository;

    @Override
    public Master addMaster(Master master) {
        return masterRepository.save(master);
    }

    @Override
    public Master getMaster(Long id) {
        return masterRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Not found master by id"));
    }

    @Override
    public Master getMaster() {
        Master master = masterRepository.findAll().get(0);
        MasterDto masterDto = MasterDto.of(master);
        return masterRepository.findAll().get(0);
    }
}
