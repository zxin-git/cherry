package com.zxin.java.cherry.finance;

import java.math.BigDecimal;

/**
 * @author zxin
 */
public class InterestUtils {

    public static BigDecimal compoundInterest(BigDecimal interest, int time){
        BigDecimal base = BigDecimal.ONE.add(interest);
        BigDecimal compoundInterest = BigDecimal.ONE;
        for (int i = 1; i <= time; i++) {
            compoundInterest = compoundInterest.multiply(base);
        }
        return compoundInterest;
    }

    public static BigDecimal timeInterest(BigDecimal interest, int time){
        BigDecimal timeInterest = interest.multiply(new BigDecimal(time));
        return BigDecimal.ONE.add(timeInterest);
    }
//    BigDecimal corpus,
}
