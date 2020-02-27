package com.zxin.java.fund.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.io.Files;
import com.zxin.java.fund.repository.FundCompanyRepository;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class FundCompanyEntityTest {

    @Autowired
    private FundCompanyRepository repository;

    @Test
    void insert() {
        FundCompanyEntity fundCompanyEntity = new FundCompanyEntity();
        fundCompanyEntity.setCode(ThreadLocalRandom.current().nextInt(1000)+"");
        fundCompanyEntity.setCreateDate(LocalDate.now());
        fundCompanyEntity.setEnglishName("en name");
        fundCompanyEntity.setLegalName("le name");
        fundCompanyEntity.setShortName("sss");
        fundCompanyEntity.setCharCode("aaa");
        repository.save(fundCompanyEntity);
    }

    @Test
    void list() {
        List<FundCompanyEntity> list = repository.findAll();
        System.err.println(list);
    }

    @Test
    void delete() {
        repository.deleteAll();
    }

    @Test
    void jsonInit() {
        File file = new File("C:\\Users\\zxin\\Documents\\基金\\company.json");
        try  {
            String jsonStr = Files.toString(file , Charset.defaultCharset());
            List<FundCompanyEntity> list = new ArrayList<>();
            JSONArray datas = JSON.parseObject(jsonStr).getJSONArray("datas");
            for (int i = 0; i < datas.size(); i++) {
                JSONArray jsonArray = datas.getJSONArray(i);
                list.add(fundCompanyEntity(jsonArray));
            }
            long start = System.currentTimeMillis();
//            list.stream().forEach(repository::save);
            list.stream().parallel().forEach(repository::save);
//            repository.saveAll(list);
//            System.err.println(list);
            System.err.println(System.currentTimeMillis() - start);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    FundCompanyEntity fundCompanyEntity(JSONArray jsonArray){
        FundCompanyEntity fundCompanyEntity = new FundCompanyEntity();
        try {
            fundCompanyEntity.setCode(jsonArray.getString(0));
            fundCompanyEntity.setLegalName(jsonArray.getString(1));
            fundCompanyEntity.setCreateDate(LocalDate.parse(jsonArray.getString(2)));
            fundCompanyEntity.setCharCode(jsonArray.getString(5));
            fundCompanyEntity.setShortName(jsonArray.getString(9));
        } catch (Exception e) {
            System.err.println(jsonArray.toJSONString());
        }
        return fundCompanyEntity;
    }
}