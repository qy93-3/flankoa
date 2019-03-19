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
 * InnoDB free: 7168 kB; (`status_id`) REFER `flank_oa/oa_status`(`status_id`) ON D
 * </p>
 *
 * @author Flank
 * @since 2019-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("oa_attends")
public class Attends extends Model<Attends> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "attends_id", type = IdType.AUTO)
    private Integer attendsId;

    private String type;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date time;

    private Integer statusId;

    private Integer userId;


    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private Status status;


    @Override
    protected Serializable pkVal() {
        return this.attendsId;
    }

}
