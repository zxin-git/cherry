package com.zxin.java.http.call;


import com.zxin.java.http.Result;
import com.zxin.java.http.ResultException;
import com.zxin.java.http.convert.IResponseExtractor;
import com.zxin.java.http.convert.impl.StringResponseExtractor;
import com.zxin.java.http.okhttp.call.DefaultOkHttpCaller;
import com.zxin.java.http.okhttp.call.IOkHttpCaller;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.HttpStatus;

import java.io.IOException;

/**
 * @author zxin
 */
public class DefaultHttpService implements IHttpService {

    private IOkHttpCaller okHttpCaller = new DefaultOkHttpCaller();

    @Override
    public <T> Result<T> send(OkHttpClient httpClient, Request request, IResponseExtractor<T> responseExtractor) throws ResultException {
        try {
            Response response = okHttpCaller.call(httpClient, request);

            HttpStatus httpStatus = HttpStatus.valueOf(response.code());
            if(HttpStatus.OK.equals(httpStatus)){
                Result<T> result = responseExtractor.extractData(response);
                return result;
            }else{
                Result<String> result = StringResponseExtractor.getInstance().extractData(response);
                throw new ResultException(result);
            }
        } catch (IOException e) {
            throw new ResultException(request.url().toString(), e);
        }
    }


    private static final DefaultHttpService INSTANCE = new DefaultHttpService();

    private DefaultHttpService(){}

    public static DefaultHttpService getInstance(){
        return INSTANCE;
    }

}