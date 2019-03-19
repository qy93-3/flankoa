package com.flank.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.flank.beans.Attends;
import com.flank.mapper.AttendsMapper;
import com.flank.service.AttendsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * InnoDB free: 7168 kB; (`status_id`) REFER `flank_oa/oa_status`(`status_id`) ON D 服务实现类
 * </p>
 *
 * @author Flank
 * @since 2019-03-08
 */
@Service
public class AttendsServiceImpl extends ServiceImpl<AttendsMapper, Attends> implements AttendsService {

}
