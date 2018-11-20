package com.hwh.po;

import java.util.Date;

/**
 * @author hwh
 * @create 2018/11/20 11:24
 */
public class VisitLog {

    private String id;

    private Date visitdate;

    private String opername;

    private String userid;

    public VisitLog(String id, Date visitdate, String opername, String userid) {
        this.id = id;
        this.visitdate = visitdate;
        this.opername = opername;
        this.userid = userid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getVisitdate() {
        return visitdate;
    }

    public void setVisitdate(Date visitdate) {
        this.visitdate = visitdate;
    }

    public String getOpername() {
        return opername;
    }

    public void setOpername(String opername) {
        this.opername = opername;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
