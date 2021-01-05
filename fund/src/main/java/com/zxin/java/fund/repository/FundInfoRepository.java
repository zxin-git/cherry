package com.zxin.java.fund.repository;

import com.zxin.java.fund.entity.FundEntity;
import com.zxin.java.fund.entity.FundInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author zxin
 */
@Repository
public interface FundInfoRepository extends JpaRepository<FundInfo, String> {

    FundInfo findByCode(String code);

}
