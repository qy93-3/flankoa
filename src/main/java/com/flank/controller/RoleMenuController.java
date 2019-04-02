package com.flank.controller;

import com.flank.beans.Menu;
import com.flank.beans.Role;
import com.flank.beans.RoleMenu;
import com.flank.beans.UserRole;
import com.flank.result.Result;
import com.flank.service.MenuService;
import com.flank.service.RoleMenuService;
import com.flank.service.UserRoleService;
import com.flank.utils.TreeBuilder;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * <p>
 * InnoDB free: 7168 kB; (`menu_id`) REFER `flank_oa/oa_menu`(`menu_id`) ON UPDATE  前端控制器
 * </p>
 *
 * @author Flank
 * @since 2019-03-08
 */
@RestController
@RequestMapping("/roleMenu")
public class RoleMenuController {
    @Autowired
    RoleMenuService roleMenuService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    MenuService menuService;

    @RequestMapping("/menuInfo")
    public  Object  menuInfo(HttpSession session) {
        /*int userId = Integer.parseInt(session.getId());*/
        //根据登录用户的id获取所对应角色的id
        UserRole userRole = userRoleService.selectById(1);
        int roleId = userRole.getRoleId();
        //通过角色id获取对应菜单的ids
        List<RoleMenu> roleMenus = roleMenuService.selectList(null);
        List<Integer> menuIds = new ArrayList<>();
        for (RoleMenu roleMenu : roleMenus) {
            if (roleMenu.getRoleId() == roleId) {
                menuIds.add(roleMenu.getMenuId());
            }
        }
        //通过ids获取对应的菜单
        List<Menu> menus = menuService.selectBatchIds(menuIds);

        //根据菜单封装需要的格式
        List<Menu> menuList=TreeBuilder.build(menus);

        Result<Menu> trees = new Result<Menu>();
        trees.setCode(0).setCount(menuList.size()).setMsg("success").setData(menuList);

        return trees;
    }

    @GetMapping("/grantRoleInfo")
    public Object grantRole(HttpSession session) {
        /*int userId = Integer.parseInt(session.getId());*/
        //根据登录用户的id获取所对应角色的id
        UserRole userRole = userRoleService.selectById(1);
        int roleId = userRole.getRoleId();
        //通过角色id获取对应菜单的ids
        List<RoleMenu> roleMenus = roleMenuService.selectList(null);
        List<Integer> menuIds = new ArrayList<>();
        for (RoleMenu roleMenu : roleMenus) {
            if (roleMenu.getRoleId() == roleId) {
                menuIds.add(roleMenu.getMenuId());
            }
        }
        //通过ids获取对应的菜单
        List<Menu> menus = menuService.selectBatchIds(menuIds);

        //根据菜单封装需要的格式
        List<Menu> menuList=TreeBuilder.build(menus);

        return menuList;
    }

    /**
     * 角色授权
     * @param
     * @return
     */
    @PostMapping("/grant")
    @ResponseBody
    public Result grant(@Param("roleId") String roleId,@Param("menuIds")Integer[] menuIds) {
       /* RoleMenu roleMenu=new RoleMenu();
        roleMenuService.deleteById(Integer.parseInt(roleId));
        for (Integer menuId:menuIds) {
            roleMenu.setRoleId(Integer.parseInt(roleId));
            roleMenu.setMenuId(menuId);
            System.out.println(roleMenu.toString());
           *//* roleMenuService.insert(roleMenu);*//*
        }*/
        System.out.println(roleId);
        for (Integer menuId:menuIds
             ) {
            System.out.println(menuId);
        }
        return new Result<Role>().setCode(200).setMsg("授权成功");
    }
}
