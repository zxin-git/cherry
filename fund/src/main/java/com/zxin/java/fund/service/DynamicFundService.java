package com.zxin.java.fund.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.zxin.java.common.JacksonMapper;
import com.zxin.java.common.LocalDateUtil;
import com.zxin.java.fund.bo.FundFee;
import com.zxin.java.fund.dto.FeeData;
import com.zxin.java.fund.dto.InfoData;
import com.zxin.java.fund.entity.FundDynamic;
import com.zxin.java.fund.entity.FundEntity;
import com.zxin.java.fund.repository.FundDynamicRepository;
import com.zxin.java.fund.repository.FundRepository;
import com.zxin.java.http.HttpUtils;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Line;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zxin
 */
@Service
public class DynamicFundService {

    @Autowired
    private HttpService httpService;

    @Autowired
    private FundDynamicRepository dynamicRepository;

    @Autowired
    private FundRepository fundRepository;

    public void request(String company){
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("type2", "x3008");
        queryMap.put("jjgs", company);
        queryMap.put("page", "1");
        queryMap.put("num", "100");
        Request request = new Request.Builder()
                .url("http://stock.finance.sina.com.cn/fundfilter/api/openapi.php/MoneyFinanceFundFilterService.getFundFilterAll" + HttpUtils.queryString(queryMap))
                .method("GET", null)
                .build();
        String raw = httpService.response(request);
        List<InfoData> infoDataList = data(raw, new TypeReference<List<InfoData>>() {});
        infoDataList.stream().parallel().forEach(data -> {
            FundEntity fundEntity = toEntity(data, company, "Index");
            FundDynamic fundDynamic = toDynamicEntity(data);
            dynamicRepository.save(fundDynamic);
            fundRepository.save(fundEntity);
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

    public FundDynamic toDynamicEntity(InfoData data){
        FundDynamic fundDynamic = new FundDynamic();
        fundDynamic.setCode(data.getSymbol());
        fundDynamic.setFundName(data.getName());
        fundDynamic.setDataDate(LocalDate.now());
        fundDynamic.setScale(data.getJjgm());
        fundDynamic.setCxpj(data.getCxpj());
        fundDynamic.setHtpj(data.getHtpj());
        fundDynamic.setJajxpj(data.getJajxpj());
        fundDynamic.setZspj(data.getZspj());
        fundDynamic.setYhpj(data.getYhpj3());
        return fundDynamic;
    }

    public FundEntity toEntity(InfoData data, String company, String type){
        FundEntity fundEntity = new FundEntity();
        fundEntity.setCode(data.getSymbol());
        fundEntity.setFullName(data.getName());
        try{
            LocalDate localDate = LocalDate.parse(data.getClrq(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            fundEntity.setCreateDate(localDate);
        }catch (Exception e){
            e.printStackTrace();
        }
        fundEntity.setType(type);
        fundEntity.setManagementCompanyCode(company);
        return fundEntity;
    }


}
