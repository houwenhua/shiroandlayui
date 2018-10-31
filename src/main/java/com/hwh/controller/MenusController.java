package com.hwh.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hwh.service.MenusService;
import com.hwh.vo.MenusVo;
import org.apache.shiro.SecurityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hwh
 * @create 2018/10/9 10:51
 */
@RestController
@RequestMapping("/menusController")
public class MenusController {

    @Autowired
    private MenusService ms;

    @RequestMapping(value = "/findMenus",produces = "application/json;charset=utf-8")
    public Map findMenus() {
        //获得登录用户的账号
        String usercode = (String) SecurityUtils.getSubject().getPrincipal();
        //根据登录的用户查找用户对应的权限资源
        List<MenusVo> list = ms.getMenus(usercode);
        //封装成json格式然后输入到一个.json文件夹
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        //String jsonStr = JSON.toJSONString(list);
        map.put("data",list);
        /*JSONObject json = new JSONObject(map);
        try {
            wirteJsonToFile(json);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return map;
    }

    /**
     * 将json字符串写入文件中
     * @param json
     */
    private void wirteJsonToFile(JSONObject json)throws IOException {
        URL url = MenusController.class.getResource("/");
        String path = url.getPath();
        path=path.substring(0, path.indexOf("shiroandlayui"));
        File file = new File(path+"/shiroandlayui/src/main/webapp/json/nav.json");
        if (!file.exists()) {
            file.createNewFile();
        }
        //FileWriter fw = new FileWriter(file.getAbsoluteFile());
        OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile()),"UTF-8");
        BufferedWriter bw = new BufferedWriter(fw);
        json.write(bw);
        bw.close();
        System.out.println("end");
    }
}
