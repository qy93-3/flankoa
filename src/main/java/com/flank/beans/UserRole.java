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
 * InnoDB free: 7168 kB; (`role_id`) REFER `flank_oa/oa_role`(`role_id`) ON UPDATE 
 * </p>
 *
 * @author Flank
 * @since 2019-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("oa_user_role")
public class UserRole extends Model<UserRole> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uer_id", type = IdType.AUTO)
    private Integer uerId;

    private Integer roleId;


    @Override
    protected Serializable pkVal() {
        return this.uerId;
    }

}
