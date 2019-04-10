package com.hwh.service;

import com.hwh.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author hwh
 * @create 2019/4/10 16:02
 */
@Service
public class CommentService {

    @Autowired
    private CommentMapper cm;
}
