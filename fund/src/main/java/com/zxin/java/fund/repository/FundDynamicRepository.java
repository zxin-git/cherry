package com.zxin.java.fund.repository;

import com.zxin.java.fund.entity.FundDynamic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zxin
 */
@Repository
public interface FundDynamicRepository extends JpaRepository<FundDynamic, Integer> {

}
