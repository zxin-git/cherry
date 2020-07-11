package com.zxin.java.http.okhttp.call;


import com.zxin.java.http.okhttp.OkHttpUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author zxin
 */
public class DefaultOkHttpCaller implements IOkHttpCaller {

    @Override
    public Response call(OkHttpClient httpClient, Request request) throws IOException {
        return OkHttpUtils.call(httpClient, request);
    }

}
