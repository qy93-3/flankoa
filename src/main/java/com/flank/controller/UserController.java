package com.flank.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.flank.beans.Dept;
import com.flank.beans.User;
import com.flank.result.Result;
import com.flank.service.DeptService;
import com.flank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * InnoDB free: 7168 kB; (`dept_id`) REFER `flank_oa/oa_dept`(`dept_id`) ON DELETE  前端控制器
 * </p>
 *
 * @author Flank
 * @since 2019-03-08
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    DeptService deptService;


    /**
     * 登录验证
     *
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public Result login(String username, String password, HttpSession session) {
        User user = userService.selectOne(new EntityWrapper<User>().eq("user_name", username).eq("password", password));
        if (user != null) {
            //如果用户名和密码正确将该用户放到session中
            //设置session的失效时间为6个小时
            session.setMaxInactiveInterval(60*60*6);
            session.setAttribute("user", user);
            return new Result().setCode(200);
        } else {
            return new Result().setCode(0);
        }
    }

    /**
     * 锁屏登录验证
     *
     * @return
     */
    @PostMapping("/lock")
    @ResponseBody
    public Result login(String password, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return new Result().setCode(0).setMsg("用户信息保存时间已过，请重新登陆");
        }
        if (password.equals(user.getPassword())) {
            return new Result().setCode(200);
        } else {
            return new Result().setCode(0).setMsg("密码不正确");
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "index";
    }

    /**
     * 锁屏页面
     *
     * @return
     */
    @GetMapping("/lock")
    public String lock() {
        return "lock";
    }

    /**
     * 首页 导航页
     *
     * @return
     */
    @GetMapping("/nav")
    public String test() {
        return "nav";
    }

    /**
     * 跳转到 用户管理页面
     *
     * @return
     */
    @GetMapping("/mana")
    public String mana() {
        return "systemmana/UserMana";
    }


    /**
     * 用户修改页面
     * @return
     */
    @GetMapping("edit")
    public String edit2() {
        return "systemmana/UserEdit";
    }
    /**
     * 分页员工用户信息
     * @param page  当前页码
     * @param limit 每页数据量
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public Result listByPage(Integer page, Integer limit) {
        Page<User> userPage = userService.selectPage(new Page<User>(page, limit));
        ArrayList<User> list = new ArrayList<>();
        for (User user : userPage.getRecords()) {
            Dept dept = deptService.selectById(user.getDeptId());
            user.setDept(dept);
            list.add(user);
        }
        userPage.setRecords(list);
        //将结果包装成符合layui的格式
        Result<User> userResult = new Result<User>();
        userResult.setCode(0).setCount(userPage.getTotal()).setMsg("success").setData(userPage.getRecords());
        return userResult;
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
            userService.deleteBatchIds(del_ids);
        } else {
            Integer id = Integer.parseInt(ids);
            userService.deleteById(id);
        }
        return new Result().setCode(200);
    }

    /**
     * 编辑用户
     * @param user
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Result edit(@RequestBody User user) {
        user.updateById();
        return new Result<User>().setCode(200).setMsg("修改成功");
    }
    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Result add(@RequestBody User user) {
        userService.insert(user);
        return new Result<User>().setCode(200).setMsg("添加成功");
    }


}
