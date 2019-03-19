package com.flank.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
/**
  * @description: 对返回到页面的数据进行包装，为了符合layui的返回数据要求
  * @Time: 2019\3\12 0012 20:53
  * @auther: Flank
  */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Result<T> {
    private int code;
    private String msg;
    private long count;//数据的个数
    private List<T> data;




}
