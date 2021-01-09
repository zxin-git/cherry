package com.zxin.java.fund.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 基金净值
 * @author zxin
 */
@Data
public class FundNetValue {

    /**
     * 基金代码
     */
    private String code;

    /**
     * 日期
     */
    private LocalDate date;

    /**
     * 净值
     */
    private BigDecimal value;

}
