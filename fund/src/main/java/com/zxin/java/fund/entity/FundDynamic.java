package com.zxin.java.fund.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    private Integer cxpj;
    private Integer htpj;
    private Integer jajxpj;
    private Integer zspj;
    private Integer yhpj;

    private String compose;

}
