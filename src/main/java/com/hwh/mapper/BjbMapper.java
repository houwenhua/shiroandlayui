package com.hwh.mapper;

import com.hwh.po.Bjb;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hwh
 * @create 2019/3/14 15:47
 */
@Repository
public interface BjbMapper {

    List<Bjb> getAllBjbPage(@Param("name") String name, @Param("page") int page, @Param("limit") int limit);

    Long countBjb(@Param("name")String name);
}
