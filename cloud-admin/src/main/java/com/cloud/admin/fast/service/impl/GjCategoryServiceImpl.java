package com.cloud.admin.fast.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cloud.admin.fast.entity.GjCategory;
import com.cloud.admin.fast.mapper.GjCategoryMapper;
import com.cloud.admin.fast.service.GjCategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 古籍-类别 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-12-12
 */
@Service
public class GjCategoryServiceImpl extends ServiceImpl<GjCategoryMapper, GjCategory> implements GjCategoryService {

    @Override
    public GjCategory getOrAdd(String name, String parentId) {
        if (StrUtil.isBlank(name)) {
            return null;
        }
        GjCategory category = getByName(name);
        if (category == null) {
            category = new GjCategory();
            if (parentId != null) {
                GjCategory parent = selectById(parentId);
                if (parent != null) {
                    category.setParentId(parentId);
                    category.setLevel(parent.getLevel() + 1);
                }
            }
            category.setName(name);
            if (!insert(category)) {
                return null;
            }
        }
        return category;
    }

    @Override
    public GjCategory getByName(String name) {
        Wrapper<GjCategory> wrapper = new EntityWrapper<>();
        wrapper.eq("name", name);
        return selectOne(wrapper);
    }
}
