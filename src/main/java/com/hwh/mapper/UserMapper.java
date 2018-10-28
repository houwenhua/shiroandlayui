package com.hwh.mapper;

import com.hwh.po.User;
import com.hwh.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hwh
 * @create 2018/9/25 13:46
 */
@Repository
public interface UserMapper {

    User getUserByUserCode(String usercode);

    List<User> getAllUsers();

    List<UserVo> getAllUserVos();

    Integer addUser(User u);

    void addUserRole(@Param("usercode") String usercode, @Param("userrole") String userrole);

    void deleteUserById(String id);
}
