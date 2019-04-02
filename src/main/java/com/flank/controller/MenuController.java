package com.flank.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.flank.beans.Menu;
import com.flank.beans.Menu;
import com.flank.result.Result;
import com.flank.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/get/{id}")
    @ResponseBody
    public Menu get(@PathVariable("id") Long id) {
        return menuService.selectById(id);
    }

    @GetMapping("list")
    @ResponseBody
    public List<Menu> list() {
        return menuService.selectList(null);
    }

    /**
     * 跳转到 菜单管理页面
     * @return
     */
    @GetMapping("/mana")
    public String mana() {
        return "systemmana/MenuMana";
    }

    /**
     * 菜单修改页面
     * @return
     */
    @GetMapping("edit")
    public String edit2() {
        return "systemmana/MenuEdit";
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
    /**
     * 单选和批量删除二合一
     * @param ids id的集合
     * @return
     */
    @GetMapping("/delete")
    @ResponseBody
    public Result delete(@RequestParam("ids") String ids) {
        List<Integer> del_ids = new ArrayList<>();
        // 批量删除
        if (ids.contains("-")) {
            String[] str_ids = ids.split("-");
            // 组装id的集合
            for (String string : str_ids) {
                del_ids.add(Integer.parseInt(string));
            }
            menuService.deleteBatchIds(del_ids);
        } else {
            Integer id = Integer.parseInt(ids);
            menuService.deleteById(id);
        }
        return new Result().setCode(200);
    }

    /**
     * 编辑信息
     * @param Menu
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Result edit(@RequestBody Menu Menu) {
        Menu.updateById();
        return new Result<Menu>().setCode(200).setMsg("修改成功");
    }
    /**
     * 新增
     * @param Menu
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Result add(@RequestBody Menu Menu) {
        menuService.insert(Menu);
        return new Result<Menu>().setCode(200).setMsg("添加成功");
    }
}
