package com.zxin.java.http.okhttp.call;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * 基础调用器
 * @author zxin
 */
public interface IOkHttpCaller {

    Response call(OkHttpClient httpClient, Request request) throws IOException;

}
