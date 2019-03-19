package com.flank.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.flank.beans.Dept;
import com.flank.beans.Message;
import com.flank.beans.Role;
import com.flank.result.Result;
import com.flank.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/dept")
public class DeptController {


    @Autowired
    DeptService deptService;

    @GetMapping("/get/{id}")
    @ResponseBody
    public Dept get(@PathVariable("id") Long id) {
        return deptService.selectById(id);
    }

    @GetMapping("list")
    @ResponseBody
    public List<Dept> list() {
        return deptService.selectList(null);
    }

    /**
     * 跳转到 部门管理页面
     * @return
     */
    @GetMapping("/mana")
    public String mana() {
        return "systemmana/DeptMana";
    }

    /**
     * 部门修改页面
     * @return
     */
    @GetMapping("edit")
    public String edit2() {
        return "systemmana/DeptEdit";
    }
    /**
     * 分页部门信息
     *
     * @param page  当前页码
     * @param limit 每页数据量
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public Result listByPage(Integer page, Integer limit) {
        Page<Dept> deptPage = deptService.selectPage(new Page<Dept>(page, limit));

        //将结果包装成符合layui的格式
        Result<Dept> deptResult = new Result<Dept>();
        deptResult.setCode(0).setCount(deptPage.getTotal()).setMsg("success").setData(deptPage.getRecords());
        return deptResult;
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
            deptService.deleteBatchIds(del_ids);
        } else {
            Integer id = Integer.parseInt(ids);
            deptService.deleteById(id);
        }
        return new Result().setCode(200);
    }

    /**
     * 编辑部门
     * @param dept
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Result edit(@RequestBody Dept dept) {
        dept.updateById();
        return new Result<Dept>().setCode(200).setMsg("修改成功");
    }
    /**
     * 新增部门
     * @param dept
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Result add(@RequestBody Dept dept) {
        deptService.insert(dept);
        return new Result<Dept>().setCode(200).setMsg("添加成功");
    }
}
