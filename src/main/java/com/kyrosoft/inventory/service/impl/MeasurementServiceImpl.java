package com.kyrosoft.inventory.service.impl;

import com.kyrosoft.inventory.model.Measurement;
import com.kyrosoft.inventory.model.dto.MeasurementDTO;
import com.kyrosoft.inventory.repository.MeasurementRepository;
import com.kyrosoft.inventory.service.MeasurementService;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MeasurementServiceImpl
        extends BaseServiceImpl<Measurement, MeasurementRepository, MeasurementDTO>
        implements MeasurementService {

    private MeasurementRepository measurementRepository;

    public MeasurementServiceImpl(MeasurementRepository measurementRepository) {
        super(measurementRepository, Measurement.class);
        this.measurementRepository = measurementRepository;
    }

    protected List<Predicate> generateSearchSpecs(MeasurementDTO dto, CriteriaBuilder cb, Root<Measurement> root) {

        List<Predicate> predicates = new ArrayList<>();

        if(dto.getName() != null) {
            predicates.add(cb.like(
                    cb.lower(root.get("name")),
                    "%" + dto.getName() + "%".toLowerCase()));
        }

        if(dto.getCode() != null && !dto.getCode().isEmpty()) {
            predicates.add(cb.equal(root.get("code"), dto.getCode()));
        }

        if(dto.getIsBasicUnit() != null) {
            predicates.add(cb.equal(root.get("isBasicUnit"), dto.getIsBasicUnit()));
        }

        if(dto.getIsActive() != null) {
            predicates.add(cb.equal(root.get("isActive"), dto.getIsActive()));
        }

        return predicates;
    }
}
