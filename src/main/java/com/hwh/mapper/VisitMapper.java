package com.hwh.mapper;

import com.hwh.po.VisitLog;
import com.hwh.vo.VisitLogVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hwh
 * @create 2018/11/20 13:11
 */
@Repository
public interface VisitMapper {

    void add(VisitLog vl);

    List<VisitLogVo> findVisitLog();
}
