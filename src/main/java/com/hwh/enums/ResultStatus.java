package com.hwh.enums;

import org.springframework.http.HttpStatus;

public interface ResultStatus {

    //成功返回
  final static Integer OK = 1;

  //异常返回
    final static Integer BAD_REQUEST = 2;

    //没有权限
    final static Integer NO_AUTH = 444;

    //用户存在
    final static Integer EXIST_USER = 5;

    //该角色已经被使用，不能删除
    final static Integer EXIST_USED_ROLE = 555;
}
