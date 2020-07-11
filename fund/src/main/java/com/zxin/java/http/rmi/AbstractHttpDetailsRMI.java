package com.zxin.java.http.rmi;


import com.zxin.java.http.HttpUtils;
import com.zxin.java.http.convert.ISerializer;
import com.zxin.java.http.okhttp.OkHttpUtils;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * RMI参数设置细节，设置默认方法值，按需覆盖
 * @author zxin
 */
public abstract class AbstractHttpDetailsRMI<I, O> extends AbstractOkHttpRMI<I, O> {

    @Override
    protected Request request(I i) throws IOException {
        String url = url() + HttpUtils.queryString(params(i));

        Request request = new Request.Builder().url(url)
                .headers(headers(i))
                .method(httpMethod().name(), requestBody(i))
                .build();
        return request;
    }

    private Headers headers(I i) throws IOException{
        Map<String, String> map = new HashMap<>();
        map.putAll(headerMap(i));
        Headers headers = OkHttpUtils.headers(map);
        return headers;
    }

    protected Map<String, String> headerMap(I i){
        return new HashMap<>();
    }

    protected Map<String, String> params(I i){
        return new HashMap<>();
    }

    private RequestBody requestBody(I i) throws IOException{
        if(okhttp3.internal.http.HttpMethod.requiresRequestBody(httpMethod().name())){
            return RequestBody.create(mediaType(), bodySerializer().serialize(i));
        }else {
            return null;
        }
    }

    protected MediaType mediaType(){
        return null;
    }

    protected ISerializer<I> bodySerializer() throws IOException{
        return i -> "";
    }

    protected abstract HttpMethod httpMethod();

    protected abstract String url();

}
