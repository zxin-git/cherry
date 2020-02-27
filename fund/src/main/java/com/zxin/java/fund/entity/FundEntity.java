package com.zxin.java.fund.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author zxin
 */
@Data
public class FundEntity implements Serializable {

    private Integer id;

    private String code;

    private String fullName;

    private String shortName;

    private String type;

    private LocalDate createDate;

    private String managementCompany;

    private String hostingBank;


}
