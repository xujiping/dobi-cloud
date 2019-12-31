package com.cloud.admin.fast.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.fast.entity.GjCategory;
import com.cloud.admin.fast.mapper.GjCategoryMapper;
import com.cloud.admin.fast.service.GjCategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    @Override
    public GjCategory get(Integer id) {
        if (id == null) {
            return null;
        }
        return selectById(id);
    }

    @Override
    public GjCategory add(String name, String parentId) {
        if (StrUtil.isBlank(name)) {
            return null;
        }
        GjCategory category = getByName(name);
        if (category == null) {
            category = new GjCategory();
            category.setName(name);
            if (StrUtil.isNotBlank(parentId)) {
                // 查询上级
                GjCategory parent = get(Integer.valueOf(parentId));
                if (parent != null) {
                    category.setLevel(parent.getLevel() + 1);
                    category.setParentId(parentId);
                }
            }
            return insert(category) ? get(category.getId()) : null;
        }
        return category;
    }

    @Override
    public Page<GjCategory> list(Page<GjCategory> pageObject) {
        if (pageObject == null) {
            return selectPage(null);
        }
        Map<String, Object> condition = pageObject.getCondition();
        Wrapper<GjCategory> wrapper = new EntityWrapper<>();
        if (condition != null && condition.containsKey("level")) {
            wrapper.eq("level", condition.get("level"));
        }
        return selectPage(pageObject, wrapper);
    }
}
