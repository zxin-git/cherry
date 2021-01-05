package com.zxin.java.fund.repository;

import com.zxin.java.fund.entity.FundDesc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundDescRepository extends JpaRepository<FundDesc, String> {

}
