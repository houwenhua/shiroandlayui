package com.hwh.mapper;

import com.hwh.po.WtRelease;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hwh
 * @create 2019/4/10 16:03
 */
@Repository
public interface CommentMapper {

    //首页查找所有发布的问题
    List<WtRelease> findAllWtRelease();

}
