package com.kyrosoft.inventory.service.impl;

import com.kyrosoft.inventory.model.Product;
import com.kyrosoft.inventory.model.dto.ProductDTO;
import com.kyrosoft.inventory.repository.ProductRepository;
import com.kyrosoft.inventory.service.ProductService;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl
        extends BaseServiceImpl<Product, ProductRepository, ProductDTO>
        implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        super(productRepository, Product.class);
        this.productRepository = productRepository;
    }

    protected List<Predicate> generateSearchSpecs(ProductDTO dto, CriteriaBuilder cb, Root<Product> root) {

        List<Predicate> predicates = new ArrayList<>();

        if(dto.getName() != null) {
            predicates.add(cb.like(
                    cb.lower(root.get("name")),
                    "%" + dto.getName() + "%".toLowerCase()));
        }

        if(dto.getIsActive() != null) {
            predicates.add(cb.equal(root.get("isActive"), dto.getIsActive()));
        }

        if(dto.getCode() != null) {
            predicates.add(cb.equal(root.get("name"), dto.getCode()));
        }

        if(dto.getCategoryId() != null) {
            predicates.add(cb.equal(root.get("category").get("id"), dto.getCategoryId()));
        }

        return predicates;
    }
}
