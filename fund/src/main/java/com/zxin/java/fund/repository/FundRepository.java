package com.zxin.java.fund.repository;

import com.zxin.java.fund.entity.FundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zxin
 */
@Repository
public interface FundRepository extends JpaRepository<FundEntity, String> {

    FundEntity findByCode(String code);

    List<FundEntity> findByChargeTypeIsNull();
}
