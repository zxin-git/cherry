package com.zxin.java.http.convert;

import java.io.IOException;

/**
 *
 */
public interface IConverter<I, O> {

    O convert(I i) throws IOException;

}
