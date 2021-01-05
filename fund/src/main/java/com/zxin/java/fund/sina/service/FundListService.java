package com.zxin.java.fund.sina.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.zxin.java.common.JacksonMapper;
import com.zxin.java.fund.entity.FundDynamic;
import com.zxin.java.fund.entity.FundInfo;
import com.zxin.java.fund.repository.FundDynamicRepository;
import com.zxin.java.fund.repository.FundInfoRepository;
import com.zxin.java.fund.service.HttpService;
import com.zxin.java.fund.sina.data.FundListDTO;
import com.zxin.java.fund.sina.data.FundListRequestBO;
import com.zxin.java.http.HttpUtils;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * @author zxin
 */
@Service
public class FundListService {

    @Autowired
    private HttpService httpService;

    @Autowired
    private FundInfoRepository fundInfoRepository;

    @Autowired
    private ChargeServiceImpl chargeServiceImpl;

    @Autowired
    private FundDynamicRepository fundDynamicRepository;

    public void listFundByCompany(FundListRequestBO listRequestBO){
        Map<String, String> queryMap = listRequestBO.toMap();
        Request request = new Request.Builder()
                .url("http://stock.finance.sina.com.cn/fundfilter/api/openapi.php/MoneyFinanceFundFilterService.getFundFilterAll" + HttpUtils.queryString(queryMap))
                .method("GET", null)
                .build();
        String raw = httpService.response(request);

        List<FundListDTO> fundListDTOList = data(raw, new TypeReference<List<FundListDTO>>(){});
        fundListDTOList.stream().forEach(data -> {
            FundInfo fundInfo = toFundInfo(data, listRequestBO);
            fundInfoRepository.save(fundInfo);
            fundDynamicRepository.save(toDynamic(data));
            chargeServiceImpl.fundCharge(fundInfo.getCode());
        });
    }

    public <T> T data(String raw, TypeReference<T> toValueTypeRef){
        try {
            JsonNode jsonNode = JacksonMapper.JSON.getMapper().readTree(raw);
            JsonNode node = jsonNode.get("result").get("data").get("data");
            T t = JacksonMapper.JSON.getMapper().convertValue(node, toValueTypeRef);
            return t;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private FundInfo toFundInfo(FundListDTO data, FundListRequestBO requestBO){
        FundInfo fundInfo = new FundInfo();
        fundInfo.setCode(data.getSymbol());
        fundInfo.setShortName(data.getName());
        fundInfo.setType1(data.getType1Id());
        fundInfo.setType2(data.getType2Id());
        fundInfo.setType3(data.getType3Id());
        fundInfo.setManagementCompanyCode(requestBO.getJjgs());
        try{
            LocalDate localDate = LocalDate.parse(data.getClrq(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            fundInfo.setCreateDate(localDate);
        }catch (Exception e){
            e.printStackTrace();
        }
        return fundInfo;
    }

    public FundDynamic toDynamic(FundListDTO fundListDTO){
        FundDynamic fundDynamic = new FundDynamic();
        fundDynamic.setCode(fundListDTO.getSymbol());
        fundDynamic.setFundName(fundListDTO.getName());
        fundDynamic.setDataDate(LocalDate.now());
        fundDynamic.setScale(fundListDTO.getJjgm());
        return fundDynamic;
    }
}
