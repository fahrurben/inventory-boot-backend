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

    protected Specifications<Vendor> generateSearchSpecs(BaseDTO dto) {
        Specifications<Vendor> specs = Specifications.where(null);

        if(dto.getName() != null) {
            specs = specs.and(new Specification<Vendor>() {
                public Predicate toPredicate(Root<Vendor> root, CriteriaQuery<?> query,
                                             CriteriaBuilder builder) {

                    Predicate ret = builder.like(
                            builder.lower(root.get("name")),
                            "%" + dto.getName() + "%".toLowerCase());

                    return ret;
                }
            });
        }

        return specs;
    }
}
