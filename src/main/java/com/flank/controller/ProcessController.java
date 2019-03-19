package com.flank.controller;

import com.flank.beans.Evection;
import com.flank.beans.Holiday;
import com.flank.result.MyProcess;
import com.flank.result.Result;
import com.flank.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
  * @description: 流程管理模块
  * @Time: 2019\3\15 0015 14:23
  * @auther: Flank
  */
@Controller
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    HolidayService holidayService;

    @Autowired
    EvectionService evectionService;

    @Autowired
    UserService userService;

    @Autowired
    UrgencyService urgencyService;

    @Autowired
    ThroughService throughService;
    /**
     * 跳转到 新建流程
     * @return
     */
    @GetMapping("/addProcess")
    public String addProcess() {
        return "process/AddProcess";
    }
    /**
     * 跳转到 我的申请
     * @return
     */
    @GetMapping("/myProcessHtml")
    public String myProcessHtml() {
        return "process/MyProcess";
    }
    /**
     * 跳转到 申请审核
     * @return
     */
    @GetMapping("/processCheck")
    public String processCheck() {
        return "process/ProcessCheck";
    }
    /**
     *获取出差申请和请假申请的记录，对其 结果进行包装后，返回我的申请页面
     */
    @GetMapping("/myProcess")
    @ResponseBody
    public Result<MyProcess> myProcessResult(){
        //获取所有的请假申请单
        List<Holiday> holidays = holidayService.selectList(null);
        //用来放从请假申请单中获取到的有用信息包装后的MyProcess对象
        ArrayList<MyProcess> list = new ArrayList<>();
        for (Holiday holiday : holidays) {
            MyProcess myProcess = new MyProcess();
            myProcess.setType("请假申请").setTitle(holiday.getTitle()).setTime(holiday.getSubmitTime());
            myProcess.setName(userService.selectById(holiday.getLookUser()).getRealName());
            myProcess.setUrgency(urgencyService.selectById(holiday.getUrgencyId()).getName());
            myProcess.setStatus(throughService.selectById(holiday.getStatus()).getName());
            list.add(myProcess);
        }
        //获取所有的出差申请单
        List<Evection> evections  = evectionService.selectList(null);
        //用来放从出差申请单中获取到的有用信息包装后的MyProcess对象
        for (Evection evection : evections) {
            MyProcess myProcess = new MyProcess();
            myProcess.setType("出差申请").setTitle(evection.getTitle()).setTime(evection.getSubmitTiem());
            myProcess.setName(userService.selectById(evection.getLookUserId()).getRealName());
            myProcess.setUrgency(urgencyService.selectById(evection.getUrgencyId()).getName());
            myProcess.setStatus(throughService.selectById(evection.getStatusId()).getName());
            list.add(myProcess);
        }

        return new Result<MyProcess>().setCode(0).setData(list);
    }


}
