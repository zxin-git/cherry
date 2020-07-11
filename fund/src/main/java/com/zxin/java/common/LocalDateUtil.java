package com.zxin.java.common;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * 工具转换类
 * @author zxin
 */
public class LocalDateUtil {

    public static Date toDate(LocalDate localDate){
        Date date = Date.from(localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant());
        return date;
    }

    public static LocalDate fromDate(Date date){
        LocalDate localDate = date.toInstant().atZone(ZoneOffset.ofHours(8)).toLocalDate();
        return localDate;
    }
}
