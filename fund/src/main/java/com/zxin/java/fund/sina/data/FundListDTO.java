package com.zxin.java.fund.sina.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * url: http://stock.finance.sina.com.cn/fundfilter/api/openapi.php/MoneyFinanceFundFilterService.getFundFilterAll?jjgs=80000222&page=1&num=50&type2=x3006
 * @author zxin
 *
 * {
 *      "symbol": "000011",
 *      "name": "华夏大盘精选混合",
 *      "Type1Id": "x1001",
 *      "Type2Id": "x2001",
 *      "Type3Id": "x3006",
 *      "CompanyName": "华夏基金",
 *      "SubjectName": "大盘蓝筹",
 *      "clrq": "2004-08-11 00:00:00",
 *      "dwjz": "19.575",
 *      "ljjz": "24.515",
 *      "jjgm": "52.2",
 *      "w1navg": "0.0359",
 *      "w4navg": "0.1348",
 *      "w13navg": "0.4148",
 *      "w26navg": "0.4461",
 *      "w52navg": "0.5008",
 *      "y3navg": "0.7337",
 *      "y5navg": "1.1853",
 *      "slnavg": "35.4793",
 *      "ynyl": "0.3123",
 *      "cxpj": "3",
 *      "htpj": "4",
 *      "jajxpj": "2",
 *      "zspj": "3",
 *      "dailypv": "880",
 *      "trend": "8",
 *      "EXCHANGE": "CNSE00",
 *      "yhpj3": "2",
 *      "yhpj5": "3",
 *      "bartype": "of"
 *  },
 *
 */
@Data
public class FundListDTO {

    /**
     * 基金代码
     */
    private String symbol;
    private String name;
    private String type1Id;
    private String type2Id;
    private String type3Id;
    private String companyName;
    private String subjectName;
    /**
     * 成立日期
     */
    private String clrq;
    /**
     * 基金规模
     */
    private BigDecimal jjgm;

}
