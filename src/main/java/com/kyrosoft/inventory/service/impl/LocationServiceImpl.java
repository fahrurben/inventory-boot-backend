package com.kyrosoft.inventory.service.impl;

import com.kyrosoft.inventory.model.Location;
import com.kyrosoft.inventory.model.dto.BaseDTO;
import com.kyrosoft.inventory.repository.LocationRepository;
import com.kyrosoft.inventory.service.LocationService;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LocationServiceImpl
        extends BaseServiceImpl<Location, LocationRepository, BaseDTO>
        implements LocationService {

    private LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        super(locationRepository, Location.class);
        this.locationRepository = locationRepository;
    }

    protected List<Predicate> generateSearchSpecs(BaseDTO dto, CriteriaBuilder cb, Root<Location> root) {

        List<Predicate> predicates = new ArrayList<>();

        if(dto.getName() != null) {
            predicates.add(cb.like(
                    cb.lower(root.get("name")),
                    "%" + dto.getName() + "%".toLowerCase()));
        }

        if(dto.getIsActive() != null) {
            predicates.add(cb.equal(root.get("isActive"), dto.getIsActive()));
        }

        return predicates;
    }

}
