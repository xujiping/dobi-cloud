package com.cloud.pets.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.base.util.SpageUtil;
import com.cloud.pets.entity.Pets;
import com.cloud.pets.entity.dto.PetsDto;
import com.cloud.pets.mapper.PetsMapper;
import com.cloud.pets.service.PetsImageService;
import com.cloud.pets.service.PetsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 宠物表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-12-30
 */
@Service
public class PetsServiceImpl extends ServiceImpl<PetsMapper, Pets> implements PetsService {

    @Autowired private PetsImageService petsImageService;

    @Override
    public SpageUtil<Pets> listByPage(Map<String, Object> params, SpageUtil<Pets> spageUtil) {
        String categoryId = "categoryId";
        String speciesId = "speciesId";
        Wrapper<Pets> wrapper = new EntityWrapper<>();
        if (params.containsKey(categoryId)){
            wrapper.eq("category_id", params.get(categoryId));
        }
        if (params.containsKey(speciesId)){
            wrapper.eq("species_id", params.get(speciesId));
        }
        wrapper.eq("user_id", params.get("key"));
        Page<Pets> page = new Page<>(spageUtil.getPage(), spageUtil.getStep());
        page = selectPage(page, wrapper);
        List<Pets> records = page.getRecords();
        if (records != null && records.size() > 0){
            spageUtil.setRows(records);
        }
        int total = selectCount(wrapper);
        spageUtil.setTotal(total);
        return spageUtil;
    }

    @Override
    public boolean add(String key, Integer categoryId, Integer speciesId, String nickname, Integer age, Integer sex) {
        Date date = new Date();
        Pets pets = new Pets();
        pets.setUserId(key);
        pets.setCategoryId(categoryId);
        pets.setSpeciesId(speciesId);
        pets.setNickname(nickname);
        pets.setAge(age);
        pets.setSex(sex);
        pets.setCreateTime(date);
        pets.setUpdateTime(date);
        pets.setSurvival(1);
        return insert(pets);
    }

    @Override
    public boolean updateInfo(Long id, Integer categoryId, Integer speciesId, String nickname, Integer age,
                              Integer sex) {
        Pets pets = selectById(id);
        if (pets == null) {
            return false;
        }
        if (categoryId != null){
            pets.setCategoryId(categoryId);
        }
        if (speciesId != null){
            pets.setSpeciesId(speciesId);
        }
        if (StringUtils.isNotEmpty(nickname)){
            pets.setNickname(nickname);
        }
        if (age != null){
            pets.setAge(age);
        }
        if (sex != null){
            pets.setSex(sex);
        }
        return updateById(pets);
    }


    @Override
    public Pets get(String key, Long id) {
        Wrapper<Pets> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", key);
        wrapper.eq("id", id);
        return selectOne(wrapper);
    }

    @Override
    public PetsDto wrapper(Pets pets) {
        PetsDto petsDto = new PetsDto();
        if (pets == null){
            return petsDto;
        }
        BeanUtils.copyProperties(pets, petsDto);
        Integer id = pets.getId();
        // 封面图
        String defaultImage = petsImageService.getDefaultImage(id);
        petsDto.setImage(defaultImage);
        return petsDto;
    }
}
