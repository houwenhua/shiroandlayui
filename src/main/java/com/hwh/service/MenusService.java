package com.hwh.service;

import com.hwh.mapper.MenusMapper;
import com.hwh.po.Permission;
import com.hwh.vo.MenusVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hwh
 * @create 2018/10/9 11:24
 */
@Service
public class MenusService {

    @Autowired
    private MenusMapper menusMapper;

    public List<MenusVo> getMenus() {
        List<Permission> list = menusMapper.getMenus();

        //第一级菜单的集合定义
        List<MenusVo> menusVoList1 = new ArrayList<>();

        //二级菜单的数组,因为只定义了99个，所以当菜单条数超过99时，就会发生数组越界，而如果增加个数，就会出现浪费大量存储空间
        List<MenusVo>[] subMenu = new List[99];

        //先得到所有的一级菜单集合
        for(Permission p : list) {
            String parentids =  p.getParentids();
            String[] parentid = parentids.split("/");
            int len = parentid.length;

            //判断是第几级菜单，根据长度判断
            //第一级菜单
            if(len == 1) {
                MenusVo mv = new MenusVo(p.getId(),p.getName(),"&#xe621;",p.getUrl(),null);
                menusVoList1.add(mv);

                //根据有多少个一级菜单，就创建多少个二级菜单的集合，集合名字就subMenu+一级菜单的id
                subMenu[p.getId()] = new ArrayList<>();
            }
        }

        //再得到所有的二级菜单
        for(Permission p : list) {
            String parentids =  p.getParentids();
            String[] parentid = parentids.split("/");
            int len = parentid.length;
            if(len == 2) {
                //第二级菜单，再实现是哪个的上一级菜单，根据parentid的最后一位数判断
                for(MenusVo mv : menusVoList1) {
                    if (p.getParentid().equals(mv.getId().toString())) {
                        MenusVo mvtwo = new MenusVo(p.getId(),p.getName(),"&#xe621;",p.getUrl(),null);
                        subMenu[mv.getId()].add(mvtwo);
                        mv.setSubset(subMenu[mv.getId()]);
                    }
                }

            }
        }

        return menusVoList1;
    }

    /**
     * 加载菜单方法，实现根据用户查找菜单
     * @param usercode
     * @return
     */
    public List<MenusVo> getMenus(String usercode) {
        List<Permission> list = menusMapper.getMenus1(usercode);

        //第一级菜单的集合定义
        List<MenusVo> menusVoList1 = new ArrayList<>();

        //二级菜单的数组,因为只定义了99个，所以当菜单条数超过99时，就会发生数组越界，而如果增加个数，就会出现浪费大量存储空间
        List<MenusVo>[] subMenu = new List[99];

        //先得到所有的一级菜单集合
        for(Permission p : list) {
            String parentids =  p.getParentids();
            String[] parentid = parentids.split("/");
            int len = parentid.length;

            //判断是第几级菜单，根据长度判断
            //第一级菜单
            if(len == 1) {
                MenusVo mv = new MenusVo(p.getId(),p.getName(),"&#xe621;",p.getUrl(),null);
                menusVoList1.add(mv);

                //根据有多少个一级菜单，就创建多少个二级菜单的集合，集合名字就subMenu+一级菜单的id
                subMenu[p.getId()] = new ArrayList<>();
            }
        }

        //再得到所有的二级菜单
        for(Permission p : list) {
            String parentids =  p.getParentids();
            String[] parentid = parentids.split("/");
            int len = parentid.length;
            if(len == 2) {
                //第二级菜单，再实现是哪个的上一级菜单，根据parentid的最后一位数判断
                for(MenusVo mv : menusVoList1) {
                    if (p.getParentid().equals(mv.getId().toString())) {
                        MenusVo mvtwo = new MenusVo(p.getId(),p.getName(),"&#xe621;",p.getUrl(),null);
                        subMenu[mv.getId()].add(mvtwo);
                        mv.setSubset(subMenu[mv.getId()]);
                    }
                }

            }
        }

        return menusVoList1;
    }
}
