package com.zxin.java.http.convert;

import java.io.IOException;

/**
 * 序列化器
 * I input
 * @author zxin
 */
public interface ISerializer<I>{

    String serialize(I i) throws IOException;

}
