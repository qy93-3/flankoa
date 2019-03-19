package com.flank.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *请假申请管理模块
 * @author Flank
 * @since 2019-03-08
 */
@Controller
@RequestMapping("/holiday")
public class HolidayController {
    /**
     * 跳转到 添加请假申请单
     * @return
     */
    @GetMapping("/add")
    public String add() {
        return "process/AddHoliday";
    }


}
