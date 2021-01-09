package com.zxin.java.sina.dto;

import lombok.Data;

import java.util.List;
import java.util.Optional;

/**
 * @author zxin
 */
@Data
public class Result<T> {

    private ResultObj<T> result;

    public List<T> getData(){
        return Optional.ofNullable(result).map(ResultObj::getData).map(DataObj::getData).orElse(null);
    }

    @Data
    public static class ResultObj<T> {

        private Status status;

        private DataObj<T> data;
    }

    @Data
    public static class Status{
        private Integer code;
    }

    @Data
    public static class DataObj<T>{
        private List<T> data;
    }
}
