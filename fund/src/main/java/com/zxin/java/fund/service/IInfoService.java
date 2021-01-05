package com.zxin.java.fund.service;

import com.zxin.java.fund.entity.FundInfo;

/**
 * 基金信息服务
 */
public interface IInfoService {

    FundInfo getInfo(String code);

    void save(String code);

}
