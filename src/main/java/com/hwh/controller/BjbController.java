package com.hwh.controller;

import com.hwh.service.BjbService;
import com.hwh.vo.DataTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用于报价单的查询
 * @author hwh
 * @create 2019/3/14 15:44
 */
@Controller
@RequestMapping("/bjbController")
public class BjbController {

    @Autowired
    private BjbService bs;

    @RequestMapping(value = "/findAllBjb",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public DataTable findAllBjb(String name,int page,int limit) {
        DataTable dataTables = bs.getAllBjb(name,page,limit);
        return  dataTables;
    }
}
