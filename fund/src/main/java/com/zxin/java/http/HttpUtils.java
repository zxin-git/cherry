package com.zxin.java.http;


import com.zxin.java.http.okhttp.OkHttpUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.Map;

/**
 * @author zxin
 */
public class HttpUtils {


    private static Result<String> send(OkHttpClient httpClient, Request request) throws IOException {
            Response response = OkHttpUtils.call(httpClient, request);

            String url = response.request().url().toString();
            HttpStatus httpStatus = HttpStatus.valueOf(response.code());
            String body = response.body().string();

            Result<String> result = new Result(url, httpStatus, body);
            return result;
    }


    /**
     * 转换查询字符串
     * @param params
     * @return
     */
    public static String queryString(Map<String, String> params) {
        if (params != null && params.size() != 0) {
            StringBuilder queryStringBuilder = new StringBuilder();
            for(Map.Entry<String, String> entry : params.entrySet()){
                queryStringBuilder.append(String.format("&%s=%s", entry.getKey(), entry.getValue()));
            }
            queryStringBuilder.replace(0,1, "?");
            return queryStringBuilder.toString();
        }

        return "";
    }

}
