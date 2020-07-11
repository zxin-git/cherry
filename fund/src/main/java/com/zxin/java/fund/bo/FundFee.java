package com.zxin.java.fund.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zxin
 */
@Data
public class FundFee {

    private String chargeType;

    private BigDecimal buyCharge;

    private BigDecimal managementCharge;

    private BigDecimal hostingCharge;

    private BigDecimal serviceCharge;

    private String redeemCharge;

}
