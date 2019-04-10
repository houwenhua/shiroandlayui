package com.hwh.controller;

import com.hwh.service.CommentService;
import com.hwh.vo.WtReleaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author hwh
 * @create 2019/4/10 16:01
 */
@RestController
@RequestMapping("/commentController")
public class CommentController {

    @Autowired
    private CommentService ms;

    @RequestMapping(value = "/findAllWtreleases",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public List<WtReleaseVo> findAllWtreleases() {
        return ms.findAllWtReleases();
    }
}
