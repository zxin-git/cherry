package com.zxin.java.sina.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 基金净值
 * @author zxin
 */
@Data
public class FundNav {

    private String jjjz;

    private String ljjz;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDate fbrq;

}
