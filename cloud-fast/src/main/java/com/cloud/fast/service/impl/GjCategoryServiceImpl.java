package com.cloud.fast.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.fast.entity.GjCategory;
import com.cloud.fast.mapper.GjCategoryMapper;
import com.cloud.fast.service.GjCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 古籍-类别 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-11-08
 */
@Service
public class GjCategoryServiceImpl extends ServiceImpl<GjCategoryMapper, GjCategory> implements GjCategoryService {

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
    public GjCategory getByName(String name) {
        Wrapper<GjCategory> wrapper = new EntityWrapper<>();
        wrapper.eq("name", name);
        return selectOne(wrapper);
    }

    @Override
    public List<GjCategory> list(Map<String, Object> params) {
        Wrapper<GjCategory> wrapper = new EntityWrapper<>();
        if (params != null && params.containsKey("level")) {
            wrapper.eq("level", params.get("level"));
        }
        return selectList(wrapper);
    }
}
