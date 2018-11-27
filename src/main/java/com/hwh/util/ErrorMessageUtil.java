package com.hwh.util;

public class ErrorMessageUtil {


    public static ErrorMessage createEmFromException(Exception e, String errCode){
        ErrorMessage em = new ErrorMessage();

        if(e!=null){
            em.setErrCode(errCode);
            em.setErrMsg(e.getMessage());
        }else{
            em.setErrCode(errCode);
            em.setErrMsg("系统错误，请重试！");
        }

        return em;
    }
}
