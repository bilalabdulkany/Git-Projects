package com.simpledev.springbootjpa;

import com.simpledev.springbootjpa.service.Impl.BusinessService;
import com.simpledev.springbootjpa.validators.BusinessValidator;
import com.simpledev.springbootjpa.validators.IBusinessValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinessValidityTest {


    @Spy
    IBusinessValidator businessValidator;

    @Spy
    @Autowired
    BusinessService businessService;

    @Test
    public void TestCalculation(){

       // businessService=new BusinessService();
        assertEquals(1704,businessService.isItemValid(10,2));

    }
}
