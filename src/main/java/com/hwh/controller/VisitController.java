package com.hwh.controller;

import com.hwh.service.VisitService;
import com.hwh.util.DateUtil;
import com.hwh.vo.VisitLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;

/**
 * @author hwh
 * @create 2018/11/20 14:12
 */
@Controller
@RequestMapping("/visitController")
public class VisitController {

    @Autowired
    private VisitService vs;

    @RequestMapping(value = "/findVisitLog",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<VisitLogVo> findVisitLog() throws ParseException {
        List<VisitLogVo> list = vs.findVisitLog();
        return list;
    }
}
