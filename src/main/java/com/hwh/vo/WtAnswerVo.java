package com.hwh.vo;

/**
 * @author hwh
 * @create 2019/4/12 11:28
 */
public class WtAnswerVo {

    private String wtanswerid;

    private String answername;

    private String answerdate;

    private String answercontent;

    public WtAnswerVo() {
    }

    public WtAnswerVo(String answername, String answerdate, String answercontent) {
        this.answername = answername;
        this.answerdate = answerdate;
        this.answercontent = answercontent;
    }

    public String getWtanswerid() {
        return wtanswerid;
    }

    public void setWtanswerid(String wtanswerid) {
        this.wtanswerid = wtanswerid;
    }

    public String getAnswername() {
        return answername;
    }

    public void setAnswername(String answername) {
        this.answername = answername;
    }

    public String getAnswerdate() {
        return answerdate;
    }

    public void setAnswerdate(String answerdate) {
        this.answerdate = answerdate;
    }

    public String getAnswercontent() {
        return answercontent;
    }

    public void setAnswercontent(String answercontent) {
        this.answercontent = answercontent;
    }
}
