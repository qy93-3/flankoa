package com.flank.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.flank.beans.Menu;
import com.flank.beans.Message;
import com.flank.result.Result;
import com.flank.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

/**
 * <p>
 * InnoDB free: 7168 kB 前端控制器
 * </p>
 *
 * @author Flank
 * @since 2019-03-08
 */
@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    /**
     * 跳转到 菜单管理页面
     * @return
     */
    @GetMapping("/mana")
    public String mana() {
        return "systemmana/MenuMana";
    }

    /**
     * 分页菜单信息
     *
     * @param page  当前页码
     * @param limit 每页数据量
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public Result listByPage(Integer page, Integer limit) {
        Page<Menu> menuPage = menuService.selectPage(new Page<Menu>(page, limit));

        //将结果包装成符合layui的格式
        Result<Menu> menuResult = new Result<Menu>();
        menuResult.setCode(0).setCount(menuPage.getTotal()).setMsg("success").setData(menuPage.getRecords());
        return menuResult;
    }
}
