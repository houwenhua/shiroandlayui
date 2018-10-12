package com.hwh.mapper;

import com.hwh.po.Permission;
import com.hwh.vo.MenusVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hwh
 * @create 2018/10/9 11:25
 */
@Repository
public interface MenusMapper {

    List<Permission> getMenus();

    List<Permission> getMenus1(@Param("usercode") String usercode);
}
