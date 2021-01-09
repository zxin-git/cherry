package com.zxin.java.sina.feign.client;

import com.zxin.java.sina.dto.FundNav;
import com.zxin.java.sina.dto.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FundNavFeignClientTest {

    @Autowired
    private FundNavFeignClient feignClient;

    @Test
    public void feign() {
        Result<FundNav> result = feignClient.feign("660001", LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 8), 1);
        System.out.println(result.getData());
    }
}