package com.cloud.pets.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.base.constants.Constants;
import com.cloud.base.constants.ReturnCode;
import com.cloud.base.exception.BusinessException;
import com.cloud.pets.entity.PetsSpecies;
import com.cloud.pets.entity.vo.PetsSpeciesVo;
import com.cloud.pets.mapper.PetsSpeciesMapper;
import com.cloud.pets.service.PetsSpeciesService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 宠物品种表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-12-30
 */
@Service
public class PetsSpeciesServiceImpl extends ServiceImpl<PetsSpeciesMapper, PetsSpecies> implements PetsSpeciesService {

    @Override
    public List<PetsSpecies> getAll() {
        Wrapper<PetsSpecies> wrapper = new EntityWrapper<>();
        wrapper.eq("status", Constants.STAT_NORMAL);
        return selectList(wrapper);
    }

    @Override
    public Page<PetsSpecies> list(Page<PetsSpecies> page, Map<String, Object> params) {
        Wrapper<PetsSpecies> wrapper = new EntityWrapper<>();
        if (params != null && params.containsKey("categoryId")) {
            wrapper.eq("category_id", params.get("categoryId"));
        }
        wrapper.eq("status", Constants.STAT_NORMAL);
        page = selectPage(page, wrapper);
        if (page.getRecords().size() > 0) {
            page.setTotal(selectCount(wrapper));
        }
        return page;
    }

    @Override
    public PetsSpecies get(String name) {
        if (StrUtil.isBlank(name)) {
            return null;
        }
        Wrapper<PetsSpecies> wrapper = new EntityWrapper<>();
        wrapper.eq("name", name);
        return selectOne(wrapper);
    }

    @Override
    public boolean add(String name, Integer categoryId, Integer heat) {
        PetsSpecies petsSpecies = get(name);
        if (petsSpecies != null) {
            throw new BusinessException(ReturnCode.PET_SPECIES_EXIST);
        }
        petsSpecies = new PetsSpecies();
        petsSpecies.setName(name);
        petsSpecies.setCategoryId(categoryId);
        petsSpecies.setHeat(heat);
        petsSpecies.setCreateTime(new Date());
        petsSpecies.setStatus(Constants.STAT_NORMAL);
        return insert(petsSpecies);
    }

    @Override
    public boolean update(Integer id, String name, Integer categoryId, Integer heat, String status) {
        PetsSpecies petsSpecies = selectById(id);
        if (petsSpecies == null) {
            throw new BusinessException(ReturnCode.NOT_EXISTS);
        }
        if (StringUtils.isNotEmpty(name)) {
            petsSpecies.setName(name);
        }
        if (categoryId != null) {
            petsSpecies.setCategoryId(categoryId);
        }
        if (heat != null) {
            petsSpecies.setHeat(heat);
        }
        if (StringUtils.isNotEmpty(status)) {
            petsSpecies.setStatus(status);
        }
        return updateById(petsSpecies);
    }

    @Override
    public PetsSpeciesVo wrapper(PetsSpecies petsSpecies) {
        PetsSpeciesVo petsSpeciesVo = new PetsSpeciesVo();
        if (petsSpecies == null) {
            return petsSpeciesVo;
        }
        BeanUtils.copyProperties(petsSpecies, petsSpeciesVo);
        petsSpeciesVo.setValue(String.valueOf(petsSpecies.getId()));
        return petsSpeciesVo;
    }
}
