package com.zxin.java.fund.repository;

import com.zxin.java.fund.entity.FundCompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface FundCompanyRepository extends JpaRepository<FundCompanyEntity, String> {

}
