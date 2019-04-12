package com.hwh.po;

import java.util.Date;

/**
 * @author hwh
 * @create 2019/4/12 11:31
 */
public class WtAnswer {

    private String id;

    private String answerid;

    private String wtid;

    private String questionsid;

    private Date answerdate;

    private String answercontent;


    public WtAnswer() {
    }

    public WtAnswer(String id, String answerid, String wtid, String questionsid, Date answerdate, String answercontent) {
        this.id = id;
        this.answerid = answerid;
        this.wtid = wtid;
        this.questionsid = questionsid;
        this.answerdate = answerdate;
        this.answercontent = answercontent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnswerid() {
        return answerid;
    }

    public void setAnswerid(String answerid) {
        this.answerid = answerid;
    }

    public String getWtid() {
        return wtid;
    }

    public void setWtid(String wtid) {
        this.wtid = wtid;
    }

    public String getQuestionsid() {
        return questionsid;
    }

    public void setQuestionsid(String questionsid) {
        this.questionsid = questionsid;
    }

    public Date getAnswerdate() {
        return answerdate;
    }

    public void setAnswerdate(Date answerdate) {
        this.answerdate = answerdate;
    }

    public String getAnswercontent() {
        return answercontent;
    }

    public void setAnswercontent(String answercontent) {
        this.answercontent = answercontent;
    }
}
