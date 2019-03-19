package com.flank.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.flank.beans.RoleMenu;
import com.flank.mapper.RoleMenuMapper;
import com.flank.service.RoleMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * InnoDB free: 7168 kB; (`menu_id`) REFER `flank_oa/oa_menu`(`menu_id`) ON UPDATE  服务实现类
 * </p>
 *
 * @author Flank
 * @since 2019-03-08
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}
