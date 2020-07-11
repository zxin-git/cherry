package com.zxin.java.http.convert.impl;


import com.zxin.java.http.convert.IDeserializer;
import com.zxin.java.http.convert.IResponseExtractor;

/**
 * Singleton
 * @author zxin
 */
public class StringResponseExtractor extends AbstractResponseExtractor<String> implements IResponseExtractor<String> {

    private static final StringResponseExtractor INSTANCE = new StringResponseExtractor();

    private StringResponseExtractor(){}

    @Override
    public IDeserializer<String> deserializer() {
        return StringDeserializer.getInstance();
    }

    public static final StringResponseExtractor getInstance(){
        return INSTANCE;
    }
}
