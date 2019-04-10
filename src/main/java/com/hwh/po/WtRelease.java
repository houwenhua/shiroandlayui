package com.hwh.po;

import java.util.Date;

/**
 * @author hwh
 * @create 2019/4/10 16:15
 */
public class WtRelease {

    private String id;

    private String sysid;

    private String content;

    private Date releasedate;

    private String releasetitle;

    public WtRelease() {
    }

    public WtRelease(String id, String sysid, String content, Date releasedate) {
        this.id = id;
        this.sysid = sysid;
        this.content = content;
        this.releasedate = releasedate;
    }

    public WtRelease(String id, String sysid, String content, Date releasedate, String releasetitle) {
        this.id = id;
        this.sysid = sysid;
        this.content = content;
        this.releasedate = releasedate;
        this.releasetitle = releasetitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSysid() {
        return sysid;
    }

    public void setSysid(String sysid) {
        this.sysid = sysid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(Date releasedate) {
        this.releasedate = releasedate;
    }

    public String getReleasetitle() {
        return releasetitle;
    }

    public void setReleasetitle(String releasetitle) {
        this.releasetitle = releasetitle;
    }
}
