package com.cloud.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cloud.admin.entity.OpenChannel;
import com.cloud.admin.mapper.OpenChannelMapper;
import com.cloud.admin.service.OpenChannelService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 开放渠道 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-08-16
 */
@Service
public class OpenChannelServiceImpl extends ServiceImpl<OpenChannelMapper, OpenChannel> implements OpenChannelService {

    @Override
    public OpenChannel get(int type, String id) {
        if (StrUtil.isBlank(id)) {
            return null;
        }
        Wrapper<OpenChannel> wrapper = new EntityWrapper<>();
        wrapper.eq("type", type);
        wrapper.eq("id", id);
        return selectOne(wrapper);
    }

    @Override
    public String getUserId(int type, String id) {
        OpenChannel openChannel = get(type, id);
        if (openChannel != null) {
            return openChannel.getUserId();
        }
        return null;
    }

    @Override
    public boolean add(String id, int type, String uid, String userId) {
        OpenChannel openChannel = get(type, id);
        if (openChannel != null){
            return false;
        }
        openChannel = new OpenChannel();
        openChannel.setId(id);
        openChannel.setType(type);
        openChannel.setUnionId(uid);
        openChannel.setUserId(userId);
        return insert(openChannel);
    }
}
