package com.zxin.java.fund.repository;

import com.zxin.java.fund.entity.FundCompanyEntity;
import com.zxin.java.fund.entity.FundEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FundRepositoryTest {

    @Autowired
    private FundRepository repository;

    @Test
    void list() {
        List<FundEntity> list = repository.findAll();
        System.out.println(list);
        System.err.println(list.size());
    }
}