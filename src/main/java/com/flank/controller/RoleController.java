package com.flank.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.flank.beans.Role;
import com.flank.result.Result;
import com.flank.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 跳转到 角色管理页面
     * @return
     */
    @GetMapping("/mana")
    public String mana() {
        return "systemmana/RoleMana";
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
}
