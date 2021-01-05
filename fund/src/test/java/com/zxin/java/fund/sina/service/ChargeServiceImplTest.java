package com.zxin.java.fund.sina.service;

import com.zxin.java.fund.entity.FundCharge;
import com.zxin.java.fund.repository.FundChargeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class ChargeServiceImplTest {

    @Autowired
    private FundChargeRepository fundChargeRepository;

    @Autowired
    private ChargeServiceImpl chargeServiceImpl;

    @Test
    public void fundEntity() {
        List<FundCharge> fundChargeList = fundChargeRepository.findByChargeTypeIsNull();
        fundChargeList.stream().parallel().forEach(fundCharge -> {
            chargeServiceImpl.fundCharge(fundCharge.getCode());
        });
    }
}