package com.flank.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.flank.beans.SendEmailRecord;
import com.flank.mapper.SendEmailRecordMapper;
import com.flank.service.SendEmailRecordService;
import org.springframework.stereotype.Service;

@Service
public class SendEmailRecordServuceImpl extends ServiceImpl<SendEmailRecordMapper, SendEmailRecord> implements SendEmailRecordService {
}
