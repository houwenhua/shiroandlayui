package com.hwh.controller;

import com.hwh.service.CommentServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hwh
 * @create 2019/4/10 16:01
 */
@Controller
@RequestMapping("/commentController")
public class CommentController {

    @Autowired
    private CommentServer ms;
}
