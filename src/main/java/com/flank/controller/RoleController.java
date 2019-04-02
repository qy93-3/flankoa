package com.flank.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.flank.beans.Role;
import com.flank.result.Result;
import com.flank.service.RoleService;
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
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/get/{id}")
    @ResponseBody
    public Role get(@PathVariable("id") Long id) {
        return roleService.selectById(id);
    }

    @GetMapping("list")
    @ResponseBody
    public List<Role> list() {
        return roleService.selectList(null);
    }

    /**
     * 跳转到 角色管理页面
     * @return
     */
    @GetMapping("/mana")
    public String mana() {
        return "systemmana/RoleMana";
    }

    /**
     * 角色修改页面
     * @return
     */
    @GetMapping("edit")
    public String edit2() {
        return "systemmana/RoleEdit";
    }

    /**
     * 角色授权页面
     * @return
     */
    @GetMapping("grant")
    public String grant() {
        return "systemmana/RoleGrant";
    }

    /**
     * 分页角色信息
     *
     * @param page  当前页码
     * @param limit 每页数据量
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public Result listByPage(Integer page, Integer limit) {
        Page<Role> rolePage = roleService.selectPage(new Page<Role>(page, limit));

        //将结果包装成符合layui的格式
        Result<Role> roleResult = new Result<Role>();
        roleResult.setCode(0).setCount(rolePage.getTotal()).setMsg("success").setData(rolePage.getRecords());
        return roleResult;
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
            roleService.deleteBatchIds(del_ids);
        } else {
            Integer id = Integer.parseInt(ids);
            roleService.deleteById(id);
        }
        return new Result().setCode(200);
    }

    /**
     * 编辑信息
     * @param Role
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Result edit(@RequestBody Role Role) {
        Role.updateById();
        return new Result<Role>().setCode(200).setMsg("修改成功");
    }
    /**
     * 新增
     * @param Role
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Result add(@RequestBody Role Role) {
        roleService.insert(Role);
        return new Result<Role>().setCode(200).setMsg("添加成功");
    }
}
