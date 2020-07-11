package com.zxin.java.http.convert;

import com.zxin.java.http.Result;
import okhttp3.Response;

import java.io.IOException;

/**
 * 响应提取类
 * @param <T>
 */
public interface IResponseExtractor<T> {

    Result<T> extractData(Response response) throws IOException;

}
