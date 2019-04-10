package com.hwh.service;

import com.hwh.mapper.CommentMapper;
import com.hwh.po.WtRelease;
import com.hwh.vo.WtReleaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hwh
 * @create 2019/4/10 16:02
 */
@Service
public class CommentService {

    @Autowired
    private CommentMapper cm;

    public List<WtReleaseVo> findAllWtReleases() {
        List<WtReleaseVo> wrvList = new ArrayList<>();
        List<WtRelease> wrlise = cm.findAllWtRelease();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        for(WtRelease wr : wrlise) {
            WtReleaseVo wrv = new WtReleaseVo(wr.getSysid(),wr.getReleasetitle(),sdf.format(wr.getReleasedate()));
            wrvList.add(wrv);
        }
        return wrvList;
    }
}
