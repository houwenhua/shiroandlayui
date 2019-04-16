package com.hwh.service;

import com.hwh.mapper.CommentMapper;
import com.hwh.mapper.UserMapper;
import com.hwh.po.WtAnswer;
import com.hwh.po.WtRelease;
import com.hwh.util.UUIDUtil;
import com.hwh.vo.DataTable;
import com.hwh.vo.WtAnswerVo;
import com.hwh.vo.WtReleaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author hwh
 * @create 2019/4/10 16:02
 */
@Service
public class CommentService {

    @Autowired
    private CommentMapper cm;

    @Autowired
    private UserMapper um;

    public List<WtReleaseVo> findAllWtReleases() {
         List<WtReleaseVo> wrvList = new ArrayList<>();
        List<WtRelease> wrlise = cm.findAllWtRelease();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for(WtRelease wr : wrlise) {
            String userName = um.getUserNameById(wr.getSysid());
            WtReleaseVo wrv = new WtReleaseVo(wr.getId(),userName,wr.getReleasetitle(),sdf.format(wr.getReleasedate()));
            // 查询问题的评论条数，根据问题id
            String num = cm.getAllAnswerNumByWtId(wr.getId());
            wrv.setAnswernumber(num);
            wrvList.add(wrv);
        }
        return wrvList;
    }

    public void addWtrelease(String title, String content,String usercode) {
        //获得当前用户流水号ID,
        Date date = new Date();
        WtRelease wr = new WtRelease(UUIDUtil.getUUID(),usercode,content,date,title);
        cm.addWtrelease(wr);
    }

    public WtReleaseVo getWtreleaseDeteals(String wtid) {
        WtRelease wr = cm.getWtreleaseDeteals(wtid);
        String userName = um.getUserNameById(wr.getSysid());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        WtReleaseVo wrv = new WtReleaseVo(wr.getId(),userName,wr.getReleasetitle(),sdf.format(wr.getReleasedate()),wr.getContent());
        return wrv;
    }

    public List<WtAnswerVo> getAllAnswerByWtId(String wtid) {
        List<WtAnswer> list = cm.getAllAnswerByWtId(wtid);
        List<WtAnswerVo> wavList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for(WtAnswer wa : list) {
            String answerName = um.getUserNameById(wa.getAnswerid());
            WtAnswerVo wav = new WtAnswerVo(answerName,sdf.format(wa.getAnswerdate()),wa.getAnswercontent());
            wavList.add(wav);
        }
        return wavList;
    }

    public void submitAnswerContent(String usercode, String wtid, String answerContent) {
        //根据问题id查到提问人id
        String questionsId = cm.getQuestionIDByWtId(wtid);
        Date date = new Date();
        WtAnswer wa = new WtAnswer(UUIDUtil.getUUID(),usercode,wtid,questionsId,date,answerContent);
        cm.submitAnswerContent(wa);
    }

    public DataTable findAllWtreleasesPage(int currPage, int limt) {
        List<WtRelease> wrlise = cm.findAllWtreleasesPage(currPage,limt);
        Long count = cm.countPage();
        List<WtReleaseVo> wrvList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        for(WtRelease wr : wrlise) {
            String userName = um.getUserNameById(wr.getSysid());
            WtReleaseVo wrv = new WtReleaseVo(wr.getId(),userName,wr.getReleasetitle(),sdf.format(wr.getReleasedate()));
            // 查询问题的评论条数，根据问题id
            String num = cm.getAllAnswerNumByWtId(wr.getId());
            wrv.setAnswernumber(num);
            wrvList.add(wrv);
        }
        return new DataTable(0,"",count,wrvList);
    }

    public String getAllWtreleasesPageCount() {
        Long count = cm.countPage();
        return count.toString();
    }
}
