package com.zxin.java.fund.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.zxin.java.common.JacksonMapper;
import com.zxin.java.fund.bo.FundFee;
import com.zxin.java.fund.dto.FeeData;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zxin
 */
public class FeeServiceRaw {

    public static FundFee fee(String code){
        Request request = new Request.Builder()
                .url("https://stock.finance.sina.com.cn/fundInfo/api/openapi.php/FundPageInfoService.tabfl?symbol=" + code)
                .method("GET", null)
                .build();
        String raw = response(request);
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

    public static String response(Request request){
        try {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            Response response = client.newCall(request).execute();
            String raw = response.body().string();
            return raw;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String data(String raw){
        return raw.substring(raw.indexOf('(') + 1, raw.lastIndexOf(')'));
    }

    public static FundFee toFee(FeeData data){
        FeeData.Fldata fldata = data.getFldata();
        FundFee fundFee = new FundFee();

        fundFee.setManagementCharge(percentToBigDecimal(fldata.getGlf()));
        fundFee.setHostingCharge(percentToBigDecimal(fldata.getTgf()));
        fundFee.setBuyCharge(percentToBigDecimal(buyCharge(data.getSgqd())));
        fundFee.setRedeemCharge(redeemCharge(fldata.getRcsh()));
        if(fldata.getFwf().contains("%")){
            fundFee.setServiceCharge(percentToBigDecimal(fldata.getFwf()));
            fundFee.setChargeType("C");
        }else{
            fundFee.setChargeType("A");
        }
        return fundFee;
    }

    public static BigDecimal percentToBigDecimal(String percent){
        String value = percent.replaceAll("%", "");
        return new BigDecimal(value);
    }

    public static String buyCharge(List<FeeData.RangeRate> list){
        FeeData.RangeRate rangeRate = list.stream().filter(r -> r.getTj().startsWith("0")).findFirst().get();
        return rangeRate.getShui();
    }

    public static String redeemCharge(List<FeeData.RangeRate> list){
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

    public static void main(String[] args) {
        fee("004643");
    }

}
