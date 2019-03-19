package com.flank.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.flank.beans.Phone;
import com.flank.mapper.PhoneMapper;
import com.flank.service.PhoneService;
import org.springframework.stereotype.Service;

@Service
public class PhoneServiceImpl extends ServiceImpl<PhoneMapper, Phone> implements PhoneService {
}
