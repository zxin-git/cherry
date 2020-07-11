package com.zxin.java.fund.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author zxin
 */
@Data
public class InfoData {

    private String symbol;
    private String name;
    private String type1Id;
    private String type2Id;
    private String type3Id;
    private String companyName;
    private String subjectName;
    private String clrq;
    private BigDecimal jjgm;
    private Integer cxpj;
    private Integer htpj;
    private Integer jajxpj;
    private Integer zspj;
    private Integer yhpj3;
    private Integer yhpj5;

}
