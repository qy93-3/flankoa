package com.flank.controller;


import com.flank.beans.HolidayType;
import com.flank.beans.Type;
import com.flank.result.Result;
import com.flank.service.HolidayTypeService;
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
@RequestMapping("/holidayType")
public class HolidayTypeController {
    @Autowired
    HolidayTypeService holidayTypeService;

    /**
     * 显示请假类型列表
     * @return
     */
    @GetMapping("/list")
    @ResponseBody
    public Result list(){
        return new Result<HolidayType>().setCode(200).setData(holidayTypeService.selectList(null));
    }
}
