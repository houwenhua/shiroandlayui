package com.hwh.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hwh
 * @create 2018/11/20 13:33
 */
public class DateUtil {

    public static Date getFormatDate(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date formatDate = sdf.parse(sdf.format(date));
        return formatDate;
    }
}
