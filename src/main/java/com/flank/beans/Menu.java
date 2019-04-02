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
import java.util.List;

/**
 * <p>
 * InnoDB free: 7168 kB
 * </p>
 *
 * @author Flank
 * @since 2019-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("oa_menu")
public class Menu extends Model<Menu> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "menu_id", type = IdType.AUTO)
    private Integer menuId;

    @TableField(value = "pid")
    private Integer pid;

    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//form表单到javabean中
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")//JavaBean到js
    private Date createDate;

    private String href;

    @TableField(exist = false)
    private List<Menu> children;

    @TableField(exist = false)
    private boolean status=true;

    @Override
    protected Serializable pkVal() {
        return this.menuId;
    }

}
