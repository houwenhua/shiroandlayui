package com.hwh.service;

import com.hwh.mapper.VisitMapper;
import com.hwh.po.VisitLog;
import com.hwh.util.DateUtil;
import com.hwh.vo.VisitLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hwh
 * @create 2018/11/20 11:23
 */
@Service
public class VisitService {

    @Autowired
    private VisitMapper vm;

    public void add(VisitLog vl) {
        vm.add(vl);
    }

    public List<VisitLogVo> findVisitLog() throws ParseException {
        List<VisitLogVo> list = vm.findVisitLog();
        List<VisitLogVo> vlvList = new ArrayList<>();
        for(VisitLogVo vl : list) {
            VisitLogVo vlv = new VisitLogVo(DateUtil.getFormatDateStr(vl.getVisitdate()),vl.getUsername(),vl.getOpername());
            vlvList.add(vlv);
        }
        return vlvList;
    }
}
