package com.zxin.java.fund.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.zxin.java.common.JacksonMapper;
import com.zxin.java.fund.bo.FundFee;
import com.zxin.java.fund.dto.FeeData;
import com.zxin.java.fund.entity.FundEntity;
import com.zxin.java.fund.repository.FundRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zxin
 */
@Service
public class FeeService {

    @Autowired
    private HttpService httpService;

    public FundEntity fundEntity(FundEntity fundEntity){
        FundFee fundFee = fee(fundEntity.getCode());

        fundEntity.setBuyCharge(fundFee.getBuyCharge());
        fundEntity.setRedeemCharge(fundFee.getRedeemCharge());
        fundEntity.setManagementCharge(fundFee.getManagementCharge());
        fundEntity.setHostingCharge(fundFee.getHostingCharge());
        fundEntity.setServiceCharge(fundFee.getServiceCharge());
        fundEntity.setChargeType(fundFee.getChargeType());
        return fundEntity;
    }

    public FundFee fee(String code){
        Request request = new Request.Builder()
                .url("https://stock.finance.sina.com.cn/fundInfo/api/openapi.php/FundPageInfoService.tabfl?symbol=" + code)
                .method("GET", null)
                .build();
        String raw = httpService.response(request);
        System.out.println(raw);
        try {
            JsonNode jsonNode = JacksonMapper.JSON.getMapper().readTree(raw);
            JsonNode node = jsonNode.get("result").get("data");
            FeeData data = JacksonMapper.JSON.getMapper().convertValue(node, FeeData.class);
            FundFee fundFee = toFee(data);
            System.out.println(JacksonMapper.JSON.toString(fundFee));
            return fundFee;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    public String data(String raw){
        return raw.substring(raw.indexOf('(') + 1, raw.lastIndexOf(')'));
    }

    public FundFee toFee(FeeData data){
        FeeData.Fldata fldata = data.getFldata();
        FundFee fundFee = new FundFee();

        fundFee.setManagementCharge(percentToBigDecimal(fldata.getGlf()));
        fundFee.setHostingCharge(percentToBigDecimal(fldata.getTgf()));
        fundFee.setBuyCharge(buyCharge(data.getSgqd()));
        fundFee.setRedeemCharge(redeemCharge(fldata.getRcsh()));
        if(fldata.getFwf().contains("%")){
            fundFee.setServiceCharge(percentToBigDecimal(fldata.getFwf()));
            fundFee.setChargeType("C");
        }else{
            fundFee.setChargeType("A");
            fundFee.setServiceCharge(BigDecimal.ZERO);
        }
        return fundFee;
    }

    public BigDecimal percentToBigDecimal(String percent){
        String value = percent.replaceAll("%", "");
        try{
            return new BigDecimal(value);
        }catch (Exception e){
            return BigDecimal.ZERO;
        }
    }

    public BigDecimal buyCharge(List<FeeData.RangeRate> list){
        System.out.println(JacksonMapper.JSON.toString(list));
        return list.stream().filter(r -> r.getTj().startsWith("0")).findFirst()
                .map(FeeData.RangeRate::getShui)
                .map(this::percentToBigDecimal)
                .map(bigDecimal -> bigDecimal.divide(BigDecimal.TEN))
                .orElse(BigDecimal.ZERO);
    }

    public String redeemCharge(List<FeeData.RangeRate> list){
        Map<Integer, BigDecimal> treeMap = new TreeMap<>();
        list.stream().forEach(rangeRate -> {
            String tj = rangeRate.getTj();
            String day = tj.substring(tj.lastIndexOf("<") + 1, tj.length() - 1);
            day = day.replaceAll("=", "");
            day =  day.equals("X") ? "-1" : day;
            treeMap.put(Integer.valueOf(day), percentToBigDecimal(rangeRate.getShui()));
        });
        return JacksonMapper.JSON.toString(treeMap);
    }

}
