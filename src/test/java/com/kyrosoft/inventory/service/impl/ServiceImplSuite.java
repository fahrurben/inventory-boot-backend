package com.kyrosoft.inventory.service.impl;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
        CustomerServiceImplTest.class,
        LocationServiceImplTest.class,
        MeasurementServiceImplTest.class,
        ProductCategoryServiceImplTest.class,
        VendorServiceImplTest.class
})
public class ServiceImplSuite {
}
