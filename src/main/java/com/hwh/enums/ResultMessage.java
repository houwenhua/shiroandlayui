package com.hwh.enums;

/**
 * @author hwh
 * @create 2018/11/27 14:55
 */
public class ResultMessage {
    private static final long serialVersionUID = 1L;

    private Integer resultCode;
    private String resultMsg;

    public ResultMessage() {
    }

    public ResultMessage(Integer resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
