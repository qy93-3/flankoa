package com.flank.beans;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@TableName("oa_time")
public class Time extends Model<Time> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "time_id", type = IdType.AUTO)
    private Integer timeId;

    private java.sql.Time time;

    private String type;

    private String season;


    @Override
    protected Serializable pkVal() {
        return this.timeId;
    }

}
