package com.zxin.java.fund.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 基金实体
 * @author zxin
 */
@Entity
@Table(name = "fund")
@Data
public class FundEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

    private String fullName;

    private String shortName;

    private String letterName;

    private LocalDate createDate;

    private String type;

    private String chargeType;

    private BigDecimal buyCharge;

    private BigDecimal managementCharge;

    private BigDecimal hostingCharge;

    private BigDecimal serviceCharge;

    private String redeemCharge;

    private String managementCompanyCode;

    private String hostingBankCode;

}
