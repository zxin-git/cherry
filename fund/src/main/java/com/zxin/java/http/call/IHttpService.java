package com.zxin.java.http.call;


import com.zxin.java.http.Result;
import com.zxin.java.http.ResultException;
import com.zxin.java.http.convert.IResponseExtractor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public interface IHttpService {

    <T> Result<T> send(OkHttpClient httpClient, Request request, IResponseExtractor<T> responseExtractor) throws ResultException;

}
