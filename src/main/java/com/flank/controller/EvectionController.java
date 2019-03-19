package com.flank.controller;


import com.flank.beans.Evection;
import com.flank.service.EvectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 出差管理模块
 * @author Flank
 * @since 2019-03-08
 */
@Controller
@RequestMapping("/evection")
public class EvectionController {

    @Autowired
    EvectionService evectionService;

    /**
     * 跳转到 添加出差申请单
     * @return
     */
    @GetMapping("/add")
    public String add() {
        return "process/AddEvection";
    }

    /**
     * 添加出差申请
     */
    @PostMapping("add")
    public String add2(@RequestBody Evection evection){
        evectionService.insert(evection);
        return "process/myprocess";
    }


}
