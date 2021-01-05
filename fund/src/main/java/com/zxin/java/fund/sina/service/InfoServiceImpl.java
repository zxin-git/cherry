package com.zxin.java.fund.sina.service;

import com.zxin.java.fund.entity.FundInfo;
import com.zxin.java.fund.repository.FundInfoRepository;
import com.zxin.java.fund.service.IInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zxin
 */
@Service
public class InfoServiceImpl implements IInfoService {

    @Autowired
    private FundInfoRepository fundInfoRepository;

    @Override
    public FundInfo getInfo(String code) {

        return null;
    }

    @Override
    public void save(String code) {
        FundInfo value = getInfo(code);
        FundInfo entity = toEntity(code, value);
        fundInfoRepository.save(entity);
    }

    private FundInfo toEntity(String code, FundInfo value){
        FundInfo entity = fundInfoRepository.findByCode(code);
        BeanUtils.copyProperties(value, entity);
        return entity;
    }
}
