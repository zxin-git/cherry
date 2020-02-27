package com.zxin.java.fund.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * 基金公司
 * @author zxin
 */
@Entity
@Table(name = "fund_company")
@Data
public class FundCompanyEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String code;

    @Column(name = "legal_name", nullable = false)
    private String legalName;

    @Column(name = "english_name")
    private String englishName;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "char_code")
    private String charCode;

    @Column(name = "create_date")
    private LocalDate createDate;

    @Column(name = "registered_address")
    private String registeredAddress;

    @Column
    private String website;

    @Column
    private String phone;

}
