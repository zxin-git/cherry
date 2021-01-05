package com.zxin.java.fund.service;

import com.zxin.java.fund.bo.FundFee;
import com.zxin.java.fund.entity.FundCharge;

/**
 * 基金费率服务
 * @author zxin
 */
public interface IChargeService {

    FundFee getCharge(String code);

    void save(String code);

}
