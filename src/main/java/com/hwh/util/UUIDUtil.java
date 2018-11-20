package com.hwh.util;

import java.util.UUID;

/**
 * @author hwh
 * @create 2018/11/20 13:19
 */
public class UUIDUtil {

    public static String getUUID(){
        UUID uuid= UUID.randomUUID();
        String uuidStr=uuid.toString();
        return uuidStr;
    }
}
