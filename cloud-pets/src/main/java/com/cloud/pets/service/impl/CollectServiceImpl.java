package com.cloud.pets.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.pets.entity.Collect;
import com.cloud.pets.mapper.CollectMapper;
import com.cloud.pets.service.CollectService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 收藏表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-01-28
 */
@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {

    @Override
    public boolean add(String userId, String subject, long resouceId) {
        if (StringUtils.isEmpty(userId) || StringUtils.isEmpty(subject) || resouceId <= 0) {
            return false;
        }
        Collect collect = new Collect();
        collect.setUserId(userId);
        collect.setSubject(subject);
        collect.setResourceId(resouceId);
        collect.setCreateTime(new Date());
        return insert(collect);
    }
}
