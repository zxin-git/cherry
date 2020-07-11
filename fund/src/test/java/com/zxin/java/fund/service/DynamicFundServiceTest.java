package com.zxin.java.fund.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DynamicFundServiceTest {

    @Autowired
    private DynamicFundService dynamicFundService;

    @Test
    public void request() {
        dynamicFundService.request("80000224");
    }
}