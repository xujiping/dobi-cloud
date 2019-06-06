package com.cloud.admin.service.impl;

import com.cloud.admin.entity.SysRole;
import com.cloud.admin.mapper.SysRoleMapper;
import com.cloud.admin.service.SysRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 平台角色 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-06-06
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public boolean add(int platform, String name, String intro) {
        SysRole role = new SysRole();
        role.setPlatformId(platform);
        role.setRoleName(name);
        role.setRoleIntro(intro);
        return insert(role);
    }
}
