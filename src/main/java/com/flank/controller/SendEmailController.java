package com.flank.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.flank.beans.Message;
import com.flank.beans.Phone;
import com.flank.beans.SendEmailRecord;
import com.flank.result.Result;
import com.flank.service.PhoneService;
import com.flank.service.SendEmailRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人管理模块，包括邮件管理，日历管理，通讯录管理
 */
@Controller
@RequestMapping("/email")
public class SendEmailController {

    @Autowired
    SendEmailRecordService sendEmailRecordService;

    @Autowired
    PhoneService phoneService;

    /**
        * 跳转到 邮箱管理页面
     * @return
             */
    @GetMapping("/mana")
    public String mana() {
        return "personmana/EmailMana";
    }
    /**
     * 跳转到 通讯录管理页面
     * @return
     */
    @GetMapping("/phoneMana")
    public String phoneMana() {
        return "personmana/PhoneMana";
    }
    /**
     * 跳转到 我的日历页面
     * @return
     */
    @GetMapping("/calendar")
    public String calendar() {
        return "personmana/calendar";
    }

    /**
     * 邮件分页收件箱，发件箱，草稿箱，垃圾箱
     * @param page  当前页码
     * @param limit 每页数据量
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public Result listByPage(Integer page, Integer limit,@RequestParam(required = false) String type) {
        if (type == null) {
            type = "收件箱";
        }
        Page<SendEmailRecord> emailRecordPage = sendEmailRecordService.selectPage(new Page<SendEmailRecord>(page, limit),new EntityWrapper<SendEmailRecord>().eq("box",type));
        ArrayList<SendEmailRecord> list = new ArrayList<>();
        for (SendEmailRecord sendEmailRecord : emailRecordPage.getRecords()) {
            /**关联对象的查询
             *设置发送人和接收人
             */
            sendEmailRecord.setSend(phoneService.selectById(sendEmailRecord.getSendId()));
            sendEmailRecord.setReipient(phoneService.selectById(sendEmailRecord.getRecipientId()));
            list.add(sendEmailRecord);
        }
        //将带有关联对象的数据放回page对象中
        emailRecordPage.setRecords(list);

        //将结果包装成符合layui的格式
        Result<SendEmailRecord> emailResult = new Result<SendEmailRecord>();
        emailResult.setCode(0).setCount(emailRecordPage.getTotal()).setMsg("success").setData(emailRecordPage.getRecords());
        return emailResult;
    }

    /**
     * 通讯录全部数据
     * @return
     */
    @RequestMapping("/phoneList")
    @ResponseBody
    public Result listAll() {
        return new Result<Phone>().setCode(0).setMsg("success").setData(phoneService.selectList(null));
    }

    /**
     * 通讯录分页
     * @param page  当前页码
     * @param limit 每页数据量
     * @return
     */
    @RequestMapping("/phonePage")
    @ResponseBody
    public Result listByPageWithPhone(Integer page, Integer limit,@RequestParam(required = false) String type) {
        System.out.println(type);
        if (type == null) {
            type = "内部通讯录";
        }
        Page<Phone> phonePage = phoneService.selectPage(new Page<Phone>(page, limit),new EntityWrapper<Phone>().eq("type",type));
        ArrayList<Phone> list = new ArrayList<>();
        //将结果包装成符合layui的格式
        Result<Phone> emailResult = new Result<Phone>();
        emailResult.setCode(0).setCount(phonePage.getTotal()).setMsg("success").setData(phonePage.getRecords());
        return emailResult;
    }


    /**
     * 邮件删除--单选和批量删除二合一
     * @param ids id的集合
     * @return
     */
    @GetMapping("/delete")
    @ResponseBody
    public Result deleteEmail(@RequestParam("ids") String ids) {
        List<Integer> del_ids = new ArrayList<>();
        // 批量删除
        if (ids.contains("-")) {
            String[] str_ids = ids.split("-");
            // 组装id的集合
            for (String string : str_ids) {
                del_ids.add(Integer.parseInt(string));
            }
            sendEmailRecordService.deleteBatchIds(del_ids);
        } else {
            Integer id = Integer.parseInt(ids);
            sendEmailRecordService.deleteById(id);
        }
        return new Result().setCode(200);
    }
    /**
     * 通讯录删除--单选和批量删除二合一
     * @param ids id的集合
     * @return
     */
    @GetMapping("/deletePhone")
    @ResponseBody
    public Result deletePhone(@RequestParam("ids") String ids) {
        List<Integer> del_ids = new ArrayList<>();
        // 批量删除
        if (ids.contains("-")) {
            String[] str_ids = ids.split("-");
            // 组装id的集合
            for (String string : str_ids) {
                del_ids.add(Integer.parseInt(string));
            }
            phoneService.deleteBatchIds(del_ids);
        } else {
            Integer id = Integer.parseInt(ids);
            phoneService.deleteById(id);
        }
        return new Result().setCode(200);
    }
}
