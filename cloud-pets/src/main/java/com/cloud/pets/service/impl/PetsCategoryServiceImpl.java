package com.cloud.pets.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.base.constants.Constants;
import com.cloud.base.constants.ReturnCode;
import com.cloud.base.exception.BusinessException;
import com.cloud.pets.entity.PetsCategory;
import com.cloud.pets.mapper.PetsCategoryMapper;
import com.cloud.pets.service.PetsCategoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 宠物类别表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-12-30
 */
@Service
public class PetsCategoryServiceImpl extends ServiceImpl<PetsCategoryMapper, PetsCategory> implements PetsCategoryService {

    @Override
    public List<PetsCategory> getAll() {
        Wrapper<PetsCategory> wrapper = new EntityWrapper<>();
        wrapper.eq("status", Constants.STAT_NORMAL);
        return selectList(wrapper);
    }

    @Override
    public boolean add(String name) {
        if (StringUtils.isEmpty(name)){
            return false;
        }
        PetsCategory petsCategory = new PetsCategory();
        petsCategory.setName(name);
        return insert(petsCategory);
    }

    @Override
    public boolean update(Integer id, String name, String status) {
        PetsCategory petsCategory = selectById(id);
        if (petsCategory == null){
            throw new BusinessException(ReturnCode.NOT_EXISTS);
        }
        if (StringUtils.isNotEmpty(name)){
            petsCategory.setName(name);
        }
        if (StringUtils.isNotEmpty(status)){
            petsCategory.setStatus(status);
        }
        return updateById(petsCategory);
    }
}
