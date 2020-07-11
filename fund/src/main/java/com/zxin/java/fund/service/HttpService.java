package com.zxin.java.fund.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author zxin
 */
@Service
public class HttpService {

    public String response(Request request){
        try {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            Response response = client.newCall(request).execute();
            String raw = response.body().string();
            return raw;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
