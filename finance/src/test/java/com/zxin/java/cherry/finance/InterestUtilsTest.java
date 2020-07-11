package com.zxin.java.cherry.finance;

import org.junit.Test;

import java.math.BigDecimal;

public class InterestUtilsTest {

    @Test
    public void compoundInterest() {
        BigDecimal interest = new BigDecimal("0.2");
        BigDecimal compoundInterest = InterestUtils.compoundInterest(interest,5);
        System.out.println(compoundInterest);
    }

    @Test
    public void timeInterestTest() {
        BigDecimal interest = new BigDecimal("0.2");
        BigDecimal timeInterest = InterestUtils.timeInterest(interest, 5);
        System.out.println(timeInterest);
    }
}