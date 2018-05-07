package com.kyrosoft.inventory.service.impl;

import com.kyrosoft.inventory.model.Customer;
import com.kyrosoft.inventory.model.Vendor;
import com.kyrosoft.inventory.model.dto.BaseDTO;
import com.kyrosoft.inventory.repository.CustomerRepository;
import com.kyrosoft.inventory.repository.VendorRepository;
import com.kyrosoft.inventory.service.CustomerService;
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
public class CustomerServiceImpl
        extends BaseServiceImpl<Customer, CustomerRepository, BaseDTO>
        implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        super(customerRepository, Customer.class);
        this.customerRepository = customerRepository;
    }

    protected Specifications<Customer> generateSearchSpecs(BaseDTO dto) {
        Specifications<Customer> specs = Specifications.where(null);

        if(dto.getName() != null) {
            specs = specs.and(new Specification<Customer>() {
                public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query,
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
