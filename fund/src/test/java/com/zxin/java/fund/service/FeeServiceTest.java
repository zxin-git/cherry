package com.zxin.java.fund.service;

import com.zxin.java.fund.entity.FundEntity;
import com.zxin.java.fund.repository.FundRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class FeeServiceTest {

    @Autowired
    private FundRepository fundRepository;

    @Autowired
    private FeeService feeService;

    @Test
    public void fundEntity() {
//        FundEntity fundEntity = fundRepository.findByCode("161121");
        List<FundEntity> fundEntityList = fundRepository.findByChargeTypeIsNull();
        fundEntityList.stream().parallel().forEach(fundEntity -> {
            feeService.fundEntity(fundEntity);
            fundRepository.save(fundEntity);
        });
    }
}