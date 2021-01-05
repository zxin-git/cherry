package com.zxin.java.fund.sina.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.zxin.java.common.JacksonMapper;
import com.zxin.java.fund.bo.FundFee;
import com.zxin.java.fund.entity.FundCharge;
import com.zxin.java.fund.repository.FundChargeRepository;
import com.zxin.java.fund.service.HttpService;
import com.zxin.java.fund.service.IChargeService;
import com.zxin.java.fund.sina.data.FeeData;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zxin
 */
@Service
public class ChargeServiceImpl implements IChargeService {

    @Autowired
    private HttpService httpService;

    @Autowired
    private FundChargeRepository fundChargeRepository;


    @Override
    public void save(String code) {
        FundFee fundFee = getCharge(code);

        FundCharge fundCharge = fundChargeRepository.findByCode(code);
        if(fundCharge == null){
            fundCharge = new FundCharge();
        }
        fundCharge.setBuyCharge(fundFee.getBuyCharge());
        fundCharge.setRedeemCharge(fundFee.getRedeemCharge());
        fundCharge.setManagementCharge(fundFee.getManagementCharge());
        fundCharge.setHostingCharge(fundFee.getHostingCharge());
        fundCharge.setServiceCharge(fundFee.getServiceCharge());
        fundCharge.setChargeType(fundFee.getChargeType());

        fundChargeRepository.save(fundCharge);
    }

    @Override
    public FundFee getCharge(String code) {
        Request request = new Request.Builder()
                .url("https://stock.finance.sina.com.cn/fundInfo/api/openapi.php/FundPageInfoService.tabfl?symbol=" + code)
                .method("GET", null)
                .build();
        String raw = httpService.response(request);
        FeeData data = generatorFeeData(raw);

        FundFee fundFee = toFee(data);
        return fundFee;
    }

    private FeeData generatorFeeData(String raw){
        try {
            JsonNode jsonNode = JacksonMapper.JSON.getMapper().readTree(raw);
            JsonNode node = jsonNode.get("result").get("data");
            return JacksonMapper.JSON.getMapper().convertValue(node, FeeData.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    private FundFee toFee(FeeData data){
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

    private BigDecimal percentToBigDecimal(String percent){
        String value = percent.replaceAll("%", "");
        try{
            return new BigDecimal(value);
        }catch (Exception e){
            return BigDecimal.ZERO;
        }
    }

    private BigDecimal buyCharge(List<FeeData.RangeRate> list){
        System.out.println(JacksonMapper.JSON.toString(list));
        return list.stream().filter(r -> r.getTj().startsWith("0")).findFirst()
                .map(FeeData.RangeRate::getShui)
                .map(this::percentToBigDecimal)
                .map(bigDecimal -> bigDecimal.divide(BigDecimal.TEN))
                .orElse(BigDecimal.ZERO);
    }

    private String redeemCharge(List<FeeData.RangeRate> list){
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
