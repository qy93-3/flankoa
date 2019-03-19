package com.flank.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.flank.beans.UserRole;
import com.flank.mapper.UserRoleMapper;
import com.flank.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * InnoDB free: 7168 kB; (`role_id`) REFER `flank_oa/oa_role`(`role_id`) ON UPDATE  服务实现类
 * </p>
 *
 * @author Flank
 * @since 2019-03-08
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
