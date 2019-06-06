package com.cloud.admin.service;

import com.cloud.admin.entity.SysPermission;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-06-06
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 获取URI
     * @param id 主键ID
     * @return String
     */
    String getUri(int id);

}
