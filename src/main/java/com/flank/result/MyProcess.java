package com.flank.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
  * @description: 用来封装出差申请和请假申请的结果，以封装的格式返回到我的申请页面
  * @Time: 2019\3\16 0016 8:32
  * @auther: Flank
  */
@AllArgsConstructor
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class MyProcess {
    private String type;//费用类型
    private String title;//费用标题

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date time;//申请时间
    private String name;//审核人
    private String urgency;//紧急程度
    private String status;//状态
}
