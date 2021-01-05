package com.zxin.java.fund.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author zxin
 */
@Entity
@Data
public class FundInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;

    private String fullName;

    private String shortName;

    private LocalDate createDate;

    private String type1;

    private String type2;

    private String type3;

    private String managementCompanyCode;

    private String hostingBankCode;
}
