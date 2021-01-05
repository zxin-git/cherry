package com.zxin.java.fund.sina.data;

import lombok.Data;
import org.springframework.cglib.beans.BeanMap;

import java.util.Map;

/**
 * @author zxin
 */
@Data
public class FundListRequestBO {

    private String jjgs;

    private String type2;

    private String page;

    private String num;

    public Map<String, String> toMap(){
        return BeanMap.create(this);
    }
}
