package com.kyrosoft.inventory;

import com.kyrosoft.inventory.model.*;
import com.kyrosoft.inventory.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseTest {

    protected final String stringTest = "test";

    protected final Long idTest = 1L;

    protected final Long longTest = 1L;

    protected final Double doubleTest = 1.0;

    protected final Boolean boolTest = true;

    protected final Date dateTest = new Date(115,0,1);

    protected final String stringUpdated = "updated";

    protected final Long longUpdated = 2L;

    protected final Double doubleUpdated = 2.0;

    protected final Boolean boolUpdated = false;

    protected final Date dateUpdated = new Date(114,0,1);

    protected final Long IdTest = 1L;

    protected final Integer seedNumber = 20;

    protected List<Vendor> vendors = new ArrayList<>();

    protected List<Customer> customers = new ArrayList<>();

    protected List<Measurement> measurements = new ArrayList<>();

    protected List<Location> locations = new ArrayList<>();

    protected List<ProductCategory> productCategories = new ArrayList<>();

    protected List<Product> products =  new ArrayList<>();

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    ProductRepository productRepository;

    protected void seedFactory() {
        this.vendors = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.measurements = new ArrayList<>();
        this.locations = new ArrayList<>();
        this.productCategories = new ArrayList<>();
        this.products = new ArrayList<>();

        vendorSeed();
        customerSeed();
        measurementSeed();
        locationSeed();
        productCategoriesSeed();
        productsSeed();
    }

    protected void vendorSeed() {
        if(this.vendors.isEmpty()) {
            for (int i = 1; i <= seedNumber; i++) {
                Vendor vendor = new Vendor();
                vendor.setName(stringTest + i);
                vendor.setIsActive(boolTest);
                vendor.setCreatedBy(stringTest);
                vendor.setCreatedDate(dateTest);
                vendor.setUpdatedBy(stringTest);
                vendor.setUpdatedDate(dateTest);
                vendorRepository.save(vendor);

                this.vendors.add(vendor);
            }
        }
    }

    protected void customerSeed() {
        if(this.customers.isEmpty()) {
            for (int i = 1; i <= seedNumber; i++) {
                Customer customer = new Customer();
                customer.setName(stringTest + i);
                customer.setIsActive(boolTest);
                customer.setCreatedBy(stringTest);
                customer.setCreatedDate(dateTest);
                customer.setUpdatedBy(stringTest);
                customer.setUpdatedDate(dateTest);
                customerRepository.save(customer);

                this.customers.add(customer);
            }
        }
    }

    protected void measurementSeed() {
        if(this.measurements.isEmpty()) {
            for (int i = 1; i <= seedNumber; i++) {
                Measurement measurement = new Measurement();
                measurement.setName(stringTest + i);
                measurement.setCode(stringTest + i);
                measurement.setIsBasicUnit(boolTest);
                measurement.setUnitValue(doubleTest);
                measurement.setIsActive(boolTest);
                measurement.setCreatedBy(stringTest);
                measurement.setCreatedDate(dateTest);
                measurement.setUpdatedBy(stringTest);
                measurement.setUpdatedDate(dateTest);
                measurementRepository.save(measurement);

                this.measurements.add(measurement);
            }
        }
    }

    protected void locationSeed() {
        if(this.locations.isEmpty()) {
            for (int i = 1; i <= seedNumber; i++) {
                Location location = new Location();
                location.setName(stringTest + i);
                location.setIsActive(boolTest);
                location.setCreatedBy(stringTest);
                location.setCreatedDate(dateTest);
                location.setUpdatedBy(stringTest);
                location.setUpdatedDate(dateTest);
                locationRepository.save(location);

                this.locations.add(location);
            }
        }
    }

    protected void productCategoriesSeed() {
        if(this.productCategories.isEmpty()) {
            for (int i = 1; i <= seedNumber; i++) {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setName(stringTest + i);
                productCategory.setIsActive(boolTest);
                productCategory.setCreatedBy(stringTest);
                productCategory.setCreatedDate(dateTest);
                productCategory.setUpdatedBy(stringTest);
                productCategory.setUpdatedDate(dateTest);
                productCategoryRepository.save(productCategory);

                this.productCategories.add(productCategory);
            }
        }
    }

    protected void productsSeed() {
        if(this.products.isEmpty()) {
            for (int i = 1; i <= seedNumber; i++) {
                Product product = new Product();
                product.setName(stringTest + i);
                product.setCode(stringTest + i);
                product.setCategory(productCategories.get(0));
                product.setBasicUnit(measurements.get(0));
                product.setIsActive(boolTest);
                product.setCreatedBy(stringTest);
                product.setCreatedDate(dateTest);
                product.setUpdatedBy(stringTest);
                product.setUpdatedDate(dateTest);
                productRepository.save(product);

                this.products.add(product);
            }
        }
    }
}
