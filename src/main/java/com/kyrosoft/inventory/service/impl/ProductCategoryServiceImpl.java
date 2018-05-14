package com.kyrosoft.inventory.service.impl;

import com.kyrosoft.inventory.model.ProductCategory;
import com.kyrosoft.inventory.model.dto.BaseDTO;
import com.kyrosoft.inventory.repository.ProductCategoryRepository;
import com.kyrosoft.inventory.service.ProductCategoryService;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductCategoryServiceImpl
        extends BaseServiceImpl<ProductCategory, ProductCategoryRepository, BaseDTO>
        implements ProductCategoryService {

    private ProductCategoryRepository productCategoryRepository;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        super(productCategoryRepository, ProductCategory.class);
        this.productCategoryRepository = productCategoryRepository;
    }

    protected List<Predicate> generateSearchSpecs(BaseDTO dto, CriteriaBuilder cb, Root<ProductCategory> root) {

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
