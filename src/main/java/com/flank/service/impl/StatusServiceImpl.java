package com.flank.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.flank.beans.Status;
import com.flank.mapper.StatusMapper;
import com.flank.service.StatusService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * InnoDB free: 7168 kB 服务实现类
 * </p>
 *
 * @author Flank
 * @since 2019-03-08
 */
@Service
public class StatusServiceImpl extends ServiceImpl<StatusMapper, Status> implements StatusService {

}
