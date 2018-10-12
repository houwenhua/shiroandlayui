package com.hwh.vo;

import java.util.List;

/**
 * 数组表格展示的公共类
 * @author hwh
 * @create 2018/10/11 13:34
 */
public class DataTable {

    private Integer code;

     private String msg;

     private Long count;

     private Object data;

    public DataTable() {
    }

    public DataTable(Integer code, String msg, Long count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }


}
