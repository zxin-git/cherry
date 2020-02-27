package com.zxin.java.fund.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zxin
 */
@Data
public class FundFeeEntity implements Serializable {

    private Integer id;

    private BigDecimal managementFee;

    private BigDecimal hostingFee;

    private BigDecimal saleFee;

}
