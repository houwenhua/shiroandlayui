package com.hwh.vo;

import java.util.Date;

/**
 * @author hwh
 * @create 2018/11/20 14:14
 */
public class VisitLogVo {

    private Date visitdate;

    private String visitdatetemp;

    private String username;

    private String opername;

    public VisitLogVo() {
    }

    public VisitLogVo(String username, String opername) {
        this.username = username;
        this.opername = opername;
    }

    public VisitLogVo(Date visitdate, String username, String opername) {
        this.visitdate = visitdate;
        this.username = username;
        this.opername = opername;
    }

    public VisitLogVo(String visitdatetemp,String username, String opername) {
        this.visitdatetemp = visitdatetemp;
        this.username = username;
        this.opername = opername;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOpername() {
        return opername;
    }

    public void setOpername(String opername) {
        this.opername = opername;
    }

    public Date getVisitdate() {
        return visitdate;
    }

    public void setVisitdate(Date visitdate) {
        this.visitdate = visitdate;
    }

    public String getVisitdatetemp() {
        return visitdatetemp;
    }

    public void setVisitdatetemp(String visitdatetemp) {
        this.visitdatetemp = visitdatetemp;
    }
}
