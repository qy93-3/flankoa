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

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("oa_send_email_record")
public class SendEmailRecord extends Model<SendEmailRecord> {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer sendId;
    private Integer recipientId;
    private String title;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")//form表单到javabean中
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")//JavaBean到js
    private Date sendTime;

    private String appendixAddress;

    private String box;

    @TableField(exist = false)
    private Phone reipient;//收件人

    @TableField(exist = false)
    private Phone send;//发件人


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
