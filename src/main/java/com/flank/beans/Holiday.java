package com.flank.beans;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * InnoDB free: 7168 kB; (`look_user`) REFER `flank_oa/oa_user`(`user_id`) ON UPDAT
 * </p>
 *
 * @author Flank
 * @since 2019-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("oa_holiday")
public class Holiday extends Model<Holiday> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "holiday_id", type = IdType.AUTO)
    private Integer holidayId;

    private String cause;

    private String title;

    private Date startTime;

    private Integer days;

    private String filePath;

    private Integer urgencyId;

    private Date endTime;

    private Date submitTime;

    private Integer holidayType;

    private Integer status;

    private Integer holidayUser;

    private Integer lookUser;


    @Override
    protected Serializable pkVal() {
        return this.holidayId;
    }

}
