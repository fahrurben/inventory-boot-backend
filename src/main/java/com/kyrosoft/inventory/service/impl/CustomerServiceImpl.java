package com.kyrosoft.inventory.service.impl;

import com.kyrosoft.inventory.model.Customer;
import com.kyrosoft.inventory.model.dto.BaseDTO;
import com.kyrosoft.inventory.repository.CustomerRepository;
import com.kyrosoft.inventory.service.CustomerService;
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
public class CustomerServiceImpl
        extends BaseServiceImpl<Customer, CustomerRepository, BaseDTO>
        implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        super(customerRepository, Customer.class);
        this.customerRepository = customerRepository;
    }

    protected List<Predicate> generateSearchSpecs(BaseDTO dto, CriteriaBuilder cb, Root<Customer> root) {

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
