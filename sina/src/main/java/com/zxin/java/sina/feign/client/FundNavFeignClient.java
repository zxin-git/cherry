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

    @GetMapping("/fundInfo/api/openapi.php/CaihuiFundInfoService.getNav")
    Result<FundNav> feign(@RequestParam String symbol, @RequestParam LocalDate datefrom, @RequestParam LocalDate dateto, @RequestParam Integer page);

}
