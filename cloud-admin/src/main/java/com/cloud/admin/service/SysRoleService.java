package com.cloud.admin.service;

import com.cloud.admin.entity.SysRole;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 平台角色 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-06-06
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 创建角色
     * @param platform 平台ID
     * @param name 角色名
     * @param intro 简介
     * @return boolean
     */
    boolean add(int platform, String name, String intro);

}
