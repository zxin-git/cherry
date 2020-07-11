package com.zxin.java.http.rmi;


import com.zxin.java.http.Result;
import com.zxin.java.http.call.DefaultHttpService;
import com.zxin.java.http.call.IHttpService;
import com.zxin.java.http.convert.IDeserializer;
import com.zxin.java.http.convert.IResponseExtractor;
import com.zxin.java.http.convert.impl.AbstractResponseExtractor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;

/**
 * HTTP远程方法调用
 * @author zxin
 */
public abstract class AbstractOkHttpRMI<I, O> implements IRemoteMethod<I, O> {

    protected IHttpService httpService = DefaultHttpService.getInstance();

    protected volatile OkHttpClient httpClient = new OkHttpClient();

    @Override
    public O invoke(I i) throws IOException {
        Result<O> result = httpService.send(httpClient(), request(i), responseExtractor());
        return result.getBody();
    }

    protected OkHttpClient httpClient(){
        return httpClient;
    }

    protected abstract Request request(I i) throws IOException;

    protected IResponseExtractor<O> responseExtractor(){
        IDeserializer<O> deserializer = deserializer();

        return new AbstractResponseExtractor<O>() {
            @Override
            public IDeserializer<O> deserializer() {
                return deserializer;
            }
        };
    }

    protected abstract IDeserializer<O> deserializer();


}
