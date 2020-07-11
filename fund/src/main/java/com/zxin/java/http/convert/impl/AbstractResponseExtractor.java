package com.zxin.java.http.convert.impl;

import com.zxin.java.http.Result;
import com.zxin.java.http.convert.IDeserializer;
import com.zxin.java.http.convert.IResponseExtractor;
import okhttp3.Response;
import org.springframework.http.HttpStatus;

import java.io.IOException;

/**
 * @author zxin
 */
public abstract class AbstractResponseExtractor<T> implements IResponseExtractor<T> {

    @Override
    public Result<T> extractData(Response response) throws IOException {
        String url = response.request().url().toString();
        HttpStatus httpStatus = HttpStatus.valueOf(response.code());
        String body = response.body().string();

        Result<T> result = new Result(url, httpStatus, deserializer().deserialize(body));
        return result;
    }

    protected abstract IDeserializer<T> deserializer();
}
