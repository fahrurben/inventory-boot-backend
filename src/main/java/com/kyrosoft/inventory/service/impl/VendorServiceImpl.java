package com.kyrosoft.inventory.service.impl;

import com.kyrosoft.inventory.model.Vendor;
import com.kyrosoft.inventory.model.dto.BaseDTO;
import com.kyrosoft.inventory.repository.VendorRepository;
import com.kyrosoft.inventory.service.VendorService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class VendorServiceImpl
    extends BaseServiceImpl<Vendor, VendorRepository, BaseDTO>
    implements VendorService {

    private VendorRepository vendorRepository;

    public VendorServiceImpl(VendorRepository vendorRepository) {
        super(vendorRepository, Vendor.class);
        this.vendorRepository = vendorRepository;
    }

    protected List<Predicate> generateSearchSpecs(BaseDTO dto, CriteriaBuilder cb, Root<Vendor> root) {

        List<Predicate> predicates = new ArrayList<>();

        if(dto.getName() != null) {
            predicates.add(cb.like(
                    cb.lower(root.get("name")),
                    "%" + dto.getName() + "%".toLowerCase()));
        }

        return predicates;
    }
}
