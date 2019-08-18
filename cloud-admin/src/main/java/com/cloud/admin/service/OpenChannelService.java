package com.cloud.admin.service;

import com.cloud.admin.entity.OpenChannel;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 开放渠道 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-08-16
 */
public interface OpenChannelService extends IService<OpenChannel> {

    /**
     * 查询渠道信息
     * @param type
     * @param id
     * @return
     */
    OpenChannel get(int type, String id);

    /**
     * 获取用户ID
     * @param type
     * @param id
     * @return
     */
    String getUserId(int type, String id);

    /**
     * 新增
     * @param id
     * @param type
     * @param uid
     * @param userId
     * @return
     */
    boolean add(String id, int type, String uid, String userId);

}
