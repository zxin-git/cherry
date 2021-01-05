package com.zxin.java.fund.repository;

import com.zxin.java.fund.entity.FundCharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zxin
 */
@Repository
public interface FundChargeRepository extends JpaRepository<FundCharge, String> {

    List<FundCharge> findByChargeTypeIsNull();

    FundCharge findByCode(String code);
}
