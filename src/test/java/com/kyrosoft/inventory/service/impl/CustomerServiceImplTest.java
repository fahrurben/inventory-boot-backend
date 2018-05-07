package com.kyrosoft.inventory.service.impl;

import com.kyrosoft.inventory.Application;
import com.kyrosoft.inventory.ServiceException;
import com.kyrosoft.inventory.model.Customer;
import com.kyrosoft.inventory.model.SearchResult;
import com.kyrosoft.inventory.model.ServiceContext;
import com.kyrosoft.inventory.model.dto.BaseDTO;
import com.kyrosoft.inventory.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
public class CustomerServiceImplTest extends BaseServiceImplTest {

    @Autowired
    CustomerService customerService;

    @Before
    public void before() {
        seedFactory();
        ServiceContext.setCurrentUser(stringTest);
    }

    @Test
    public void createCustomerAndRetrieve_OK() throws ServiceException {
        Customer customer = new Customer();
        customer.setName(stringTest);
        customer.setIsActive(boolTest);

        customerService.create(customer);

        Customer created = customerService.get(customer.getId());
        assertEquals(customer.getName(), created.getName());
        assertEquals(customer.getIsActive(), created.getIsActive());
    }

    @Test
    public void updateCustomerAndRetrieve_OK() throws ServiceException {
        Customer customer = customers.get(0);
        customer.setName(stringUpdated);
        customer.setIsActive(boolUpdated);

        customerService.update(customer);
        Customer updated = customerService.get(customer.getId());
        assertEquals(customer.getName(), updated.getName());
        assertEquals(customer.getIsActive(), updated.getIsActive());
    }

    @Test
    public void searchCustomer_OK() throws ServiceException {
        BaseDTO dto = new BaseDTO();
        dto.setName("test");
        dto.setPage(1);
        dto.setSize(20);

        SearchResult<Customer> searchResult = customerService.search(dto);
        assertEquals(20, searchResult.getTotal());
    }
}
