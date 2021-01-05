package com.zxin.java.fund.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zxin
 */
@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames="code"))
public class FundCharge implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String code;

    private String fundName;

    private String chargeType;

    private BigDecimal managementCharge;

    private BigDecimal hostingCharge;

    private BigDecimal serviceCharge;

    private BigDecimal buyCharge;

    /**
     * 阶级赎回费
     */
    private String redeemCharge;
}
