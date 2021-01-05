package com.zxin.java.fund.sina.service;

import com.zxin.java.fund.sina.data.FundListRequestBO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FundListServiceTest {

    @Autowired
    public FundListService fundListService;

    @Test
    public void listFundByCompany() {
        FundListRequestBO listRequestBO = new FundListRequestBO();
        listRequestBO.setJjgs("80000229");
        listRequestBO.setType2("x3007");
        listRequestBO.setPage("1");
        listRequestBO.setNum("100");
        fundListService.listFundByCompany(listRequestBO);
    }
}