package com.whistleBlower;

import com.whistleBlower.business_logic.BusinessLogic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BusinessLogicTest {


    @Test
    public void testGetRequest(){
        BusinessLogic businessLogic = new BusinessLogic();
    }
}
