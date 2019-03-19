package com.flank.beans;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * InnoDB free: 7168 kB; (`dept_id`) REFER `flank_oa/oa_dept`(`dept_id`) ON DELETE 
 * </p>
 *
 * @author Flank
 * @since 2019-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("oa_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String userName;

    private String password;

    private String sex;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//form表单到javabean中
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")//JavaBean到js
    private Date birth;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//form表单到javabean中
    @JsonFormat(pattern = "yyyy-MM-dd ",timezone = "GMT+8")//JavaBean到js
    private Date joinTime;

    private String phone;

    private String email;

    private Integer deptId;

    private String realName;

    @TableField(exist = false)
    private Dept dept;


    @Override
    protected Serializable pkVal() {
        return this.userId;
    }

}
