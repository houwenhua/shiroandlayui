package com.hwh.service;

import com.hwh.mapper.VisitMapper;
import com.hwh.po.VisitLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
