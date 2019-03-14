package com.hwh.service;

import com.hwh.mapper.BjbMapper;
import com.hwh.po.Bjb;
import com.hwh.po.Permission;
import com.hwh.vo.DataTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hwh
 * @create 2019/3/14 15:46
 */
@Service
@Transactional
public class BjbService {

    @Autowired
    private BjbMapper bm;

    public DataTable getAllBjb(String name, int page, int limit) {
        List<Bjb> list = bm.getAllBjbPage(name,(page - 1) * limit,limit);
        //获得行数
        Long count = bm.countBjb(name);
        DataTable dataTable = new DataTable(0,"",count,list);
        return dataTable;
    }
}
