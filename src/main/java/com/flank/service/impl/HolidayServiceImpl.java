package com.flank.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.flank.beans.Holiday;
import com.flank.mapper.HolidayMapper;
import com.flank.service.HolidayService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * InnoDB free: 7168 kB; (`look_user`) REFER `flank_oa/oa_user`(`user_id`) ON UPDAT 服务实现类
 * </p>
 *
 * @author Flank
 * @since 2019-03-08
 */
@Service
public class HolidayServiceImpl extends ServiceImpl<HolidayMapper, Holiday> implements HolidayService {

}
