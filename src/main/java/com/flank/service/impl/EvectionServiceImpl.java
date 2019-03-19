package com.flank.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.flank.beans.Evection;
import com.flank.mapper.EvectionMapper;
import com.flank.service.EvectionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * InnoDB free: 7168 kB; (`look_user_id`) REFER `flank_oa/oa_user`(`user_id`) ON UP 服务实现类
 * </p>
 *
 * @author Flank
 * @since 2019-03-08
 */
@Service
public class EvectionServiceImpl extends ServiceImpl<EvectionMapper, Evection> implements EvectionService {

}
