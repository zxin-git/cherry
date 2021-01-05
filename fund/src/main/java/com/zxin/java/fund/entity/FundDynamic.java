package com.zxin.java.fund.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author zxin
 */
@Entity
@Data
public class FundDynamic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

    private String fundName;

    private LocalDate dataDate;

    private BigDecimal scale;

    private String managerCode;

    private String managerName;

}
