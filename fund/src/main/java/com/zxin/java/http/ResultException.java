package com.zxin.java.http;

import lombok.Getter;

import java.io.IOException;

/**
 *
 * 封装调用IOException 与 状态码非200异常， 与解析IOException
 * @author zxin
 */
@Getter
public class ResultException extends IOException {

    private Result<String> result;

    /**
     * IOException时
     * @param e
     */
    public ResultException(String url, IOException e){
        super(e.getMessage() , e);
        this.result = Result.error(url, null, null);
    }

    public ResultException(Result<String> result){
        super(result.toString());
        this.result = result;
    }

}
