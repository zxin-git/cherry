package com.zxin.java.http.okhttp;

import okhttp3.ConnectionPool;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zxin
 */
public class OkHttpUtils {

    public static Response call(OkHttpClient httpClient, Request request) throws IOException{
        Response response = httpClient.newCall(request).execute();
        return response;
    }

    public static Request request(String url, String method, Headers headers, RequestBody body){
        Request.Builder builder = new Request.Builder();
        builder.url(url).method(method, body);
        if(headers != null){
            builder.headers(headers);
        }
        return builder.build();
    }

    public static Headers headers(Map<String, String> headerMap){
        Headers.Builder builder = new Headers.Builder();
        if(headerMap != null){
            for (Map.Entry<String, String> entry: headerMap.entrySet()) {
                if(entry.getKey() != null && entry.getValue() != null){
                    builder.add(entry.getKey(), entry.getValue());
                }
            }
        }
        return builder.build();
    }

    public static RequestBody content(String content){
        return RequestBody.create(null, content);
    }


    public static OkHttpClient httpClient(int maxIdle, long keepAlive, long connectTimeout, long socketTimeout){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectionPool(connectionPool(maxIdle, keepAlive));

        builder.connectTimeout(connectTimeout, TimeUnit.MILLISECONDS);
        builder.readTimeout(socketTimeout, TimeUnit.MILLISECONDS).writeTimeout(socketTimeout, TimeUnit.MILLISECONDS);
        return builder.build();
    }

    public static ConnectionPool connectionPool(int maxIdle, long keepAlive){
        ConnectionPool pool = new ConnectionPool(maxIdle, keepAlive, TimeUnit.SECONDS);
        return pool;
    }


}
