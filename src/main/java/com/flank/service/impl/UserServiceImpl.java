package com.flank.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.flank.beans.User;
import com.flank.mapper.UserMapper;
import com.flank.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * InnoDB free: 7168 kB; (`dept_id`) REFER `flank_oa/oa_dept`(`dept_id`) ON DELETE  服务实现类
 * </p>
 *
 * @author Flank
 * @since 2019-03-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
