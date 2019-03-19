package com.flank.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.flank.beans.Message;
import com.flank.result.Result;
import com.flank.service.MessageService;
import com.flank.service.UrgencyService;
import com.flank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * InnoDB free: 7168 kB; (`author_id`) REFER `flank_oa/oa_user`(`user_id`) ON UPDAT 前端控制器
 * </p>
 *
 * @author Flank
 * @since 2019-03-08
 */
@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    UrgencyService urgencyService;
    @Autowired
    UserService userService;


    /**
     * 跳转到 信息展示页面
     *
     * @return
     */
    @GetMapping("/mana")
    public String mana() {
        return "message/MessageMana";
    }

    @GetMapping("/list")
    public String list() {
        return "message/MessageList";
    }

    @GetMapping("edit")
    public String edit2() {
        return "message/MessageEdit";
    }
    /**
     * 分页显示公告信息
     *
     * @param page  当前页码
     * @param limit 每页数据量
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public Result listByPage(Integer page, Integer limit) {
        Page<Message> messagePage = messageService.selectPage(new Page<Message>(page, limit));
        ArrayList<Message> list = new ArrayList<>();
        for (Message message : messagePage.getRecords()) {
            //关联对象的查询
            message.setUrgency(urgencyService.selectById(message.getUrgencyId())).setUser(userService.selectById(message.getAuthorId()));
            list.add(message);
        }
        //将带有关联对象的数据放回page对象中
        messagePage.setRecords(list);

        //将结果包装成符合layui的格式
        Result<Message> messageResult = new Result<Message>();
        messageResult.setCode(0).setCount(messagePage.getTotal()).setMsg("success").setData(messagePage.getRecords());
        return messageResult;
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
            messageService.deleteBatchIds(del_ids);
        } else {
            Integer id = Integer.parseInt(ids);
            messageService.deleteById(id);
        }
        return new Result().setCode(200);
    }

    /**
     * 编辑信息
     * @param message
     * @return
     */
    @PostMapping("/edit")
    @ResponseBody
    public Result edit(@RequestBody Message message) {
        message.updateById();
        return new Result<Message>().setCode(200).setMsg("修改成功");
    }
    /**
     * 新增
     * @param message
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Result add(@RequestBody Message message) {
        message.setAuthorId(1);
        messageService.insert(message);
        return new Result<Message>().setCode(200).setMsg("添加成功");
    }
}

