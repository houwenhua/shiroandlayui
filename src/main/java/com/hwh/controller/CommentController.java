package com.hwh.controller;

import com.hwh.service.CommentService;
import com.hwh.vo.DataTable;
import com.hwh.vo.WtAnswerVo;
import com.hwh.vo.WtReleaseVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author hwh
 * @create 2019/4/10 16:01
 */
@Controller
@RequestMapping("/commentController")
public class CommentController {

    @Autowired
    private CommentService ms;

    /**
     * 查找所有问题
     * @return
     */
    @RequestMapping(value = "/findAllWtreleases",method = RequestMethod.GET/*,produces = "application/json;charset=utf-8"*/)
    public ModelAndView findAllWtreleases() {
        List<WtReleaseVo> list = ms.findAllWtReleases();
        ModelAndView mav = new ModelAndView("/jsp/pl/plindex.jsp");
        mav.addObject("wtList",list);


        return mav;
    }

    /**
     * 查询出所有问题，并且分页
     * @param currPage 当前页
     * @param limit 每页的条数
     */
    @RequestMapping(value = "/findAllWtreleasesPage",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public DataTable findAllWtreleasesPage(int currPage,int limit){
       DataTable dt = ms.findAllWtreleasesPage((currPage-1)*limit,limit);
       return dt;
    }

    @RequestMapping(value = "/getAllWtreleasesPageCount",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getAllWtreleasesPageCount() {
        return ms.getAllWtreleasesPageCount();
    }

    /**
     * 发布问题
     */
    @RequestMapping(value = "/addWtrelease",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addWtrelease(String title,String content) {
        //获得登录用户的账号
        String usercode = (String) SecurityUtils.getSubject().getPrincipal();
        ms.addWtrelease(title,content,usercode);
        return "1";
    }

    /**
     * 打开问题详情
     * @return
     */
    @RequestMapping(value = "/getWtreleaseDeteals",produces = "application/json;charset=utf-8")
    @ResponseBody
    public WtReleaseVo getWtreleaseDeteals(String wtid) {
        WtReleaseVo wrv = ms.getWtreleaseDeteals(wtid);

        /*ModelAndView mav = new ModelAndView("/jsp/pl/pldetail.jsp");
        mav.addObject(wrv);*/
        return wrv;
    }


    /**
     * 根据问题id查找所有回帖
     * @param wtid
     */
    @RequestMapping(value = "/getAllAnswerByWtId",produces = "application/json;charset=utf-8")
    @ResponseBody
    public List<WtAnswerVo> getAllAnswerByWtId(String wtid) {
        List<WtAnswerVo> list = ms.getAllAnswerByWtId(wtid);
        return list;
    }

    @RequestMapping(value = "/submitAnswerContent",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String submitAnswerContent(String answerContent,String wtid) {
        //获得登录用户的账号,也就是回帖人
        String usercode = (String) SecurityUtils.getSubject().getPrincipal();
        ms.submitAnswerContent(usercode,wtid,answerContent);
        return "1";
    }
}
