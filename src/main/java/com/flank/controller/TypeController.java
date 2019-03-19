package com.flank.controller;


import com.flank.beans.Type;
import com.flank.beans.Urgency;
import com.flank.result.Result;
import com.flank.service.TypeService;
import com.flank.service.UrgencyService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    TypeService typeService;

    /**
     * 显示紧急程度列表
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public Result list(){
        return new Result<Type>().setCode(200).setData(typeService.selectList(null));
    }

}
