package com.flank.beans;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
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
 * InnoDB free: 7168 kB; (`author_id`) REFER `flank_oa/oa_user`(`user_id`) ON UPDAT
 * </p>
 *
 * @author Flank
 * @since 2019-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Message extends Model<Message> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//form表单到javabean中
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")//JavaBean到js
    private Date createTime;

    private Integer authorId;

    private Integer urgencyId;

    @TableField(exist = false)
    private Urgency urgency;

    @TableField(exist = false)
    private User user;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
