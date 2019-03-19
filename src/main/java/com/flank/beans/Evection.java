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
 * InnoDB free: 7168 kB; (`look_user_id`) REFER `flank_oa/oa_user`(`user_id`) ON UP
 * </p>
 *
 * @author Flank
 * @since 2019-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("oa_evection")
public class Evection extends Model<Evection> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "evection_id", type = IdType.AUTO)
    private Integer evectionId;

    private String title;

    private Date startTime;

    private Date endTime;

    private String filePath;

    private String evectionCause;

    private Integer urgencyId;

    private Integer typeId;

    private Integer statusId;

    private Date submitTiem;

    private Integer evectionUser;

    private Integer lookUserId;


    @Override
    protected Serializable pkVal() {
        return this.evectionId;
    }

}
