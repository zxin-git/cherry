package com.zxin.java.sina.feign.client;

import com.zxin.java.sina.dto.FundNav;
import com.zxin.java.sina.dto.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

/**
 * @author zxin
 */
@FeignClient(name = "nav", url = "https://stock.finance.sina.com.cn")
public interface FundNavFeignClient {

    /**
     * 获取日期范围基金的净值列表
     * @param symbol 基金代码
     * @param datefrom 开始日期
     * @param dateto 结束日期
     * @param page 分页页码，（每页固定条）
     * @return
     */
    @GetMapping("/fundInfo/api/openapi.php/CaihuiFundInfoService.getNav")
    Result<FundNav> feign(@RequestParam String symbol, @RequestParam LocalDate datefrom, @RequestParam LocalDate dateto, @RequestParam Integer page);

}
