package com.zxin.java.http.rmi;

import java.io.IOException;

/**
 *
 * 抽象RMI(remote method invocation) 接口
 * 只关注业务调用
 * @author zxin
 */
public interface IRemoteMethod<I, O> {

    O invoke(I i) throws IOException;

}
