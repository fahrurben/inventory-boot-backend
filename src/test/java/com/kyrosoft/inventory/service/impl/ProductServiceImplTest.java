package com.kyrosoft.inventory.service.impl;

import com.kyrosoft.inventory.Application;
import com.kyrosoft.inventory.ServiceException;
import com.kyrosoft.inventory.model.Product;
import com.kyrosoft.inventory.model.SearchResult;
import com.kyrosoft.inventory.model.ServiceContext;
import com.kyrosoft.inventory.model.dto.ProductDTO;
import com.kyrosoft.inventory.service.ProductService;
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
public class ProductServiceImplTest extends BaseServiceImplTest {

    @Autowired
    ProductService productService;

    @Before
    public void before() {
        seedFactory();
        ServiceContext.setCurrentUser(stringTest);
    }

    @Test
    public void searchProduct_OK() throws ServiceException {
        ProductDTO dto = new ProductDTO();
        dto.setName("test");
        dto.setPage(1);
        dto.setSize(20);

        SearchResult<Product> searchResult = productService.search(dto);
        assertEquals(20, searchResult.getTotal());
    }

    @Test
    public void searchProduct_FilterCode_OK()  throws ServiceException {
        ProductDTO dto = new ProductDTO();
        dto.setCode("test1");
        dto.setPage(1);
        dto.setSize(20);

        SearchResult<Product> searchResult = productService.search(dto);
        assertEquals(1, searchResult.getTotal());
    }

    @Test
    public void searchProduct_FilterCategory_OK()  throws ServiceException {
        ProductDTO dto = new ProductDTO();
        dto.setCategoryId(productCategories.get(0).getId());
        dto.setPage(1);
        dto.setSize(20);

        SearchResult<Product> searchResult = productService.search(dto);
        assertEquals(20, searchResult.getTotal());
    }
}
