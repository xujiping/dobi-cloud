package com.cloud.pets.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.base.constants.Constants;
import com.cloud.base.util.DateTimeUtil;
import com.cloud.base.util.SpageUtil;
import com.cloud.pets.entity.Demand;
import com.cloud.pets.entity.PetsSpecies;
import com.cloud.pets.entity.User;
import com.cloud.pets.entity.dto.DemandDto;
import com.cloud.pets.mapper.DemandMapper;
import com.cloud.pets.service.DemandService;
import com.cloud.pets.service.PetsSpeciesService;
import com.cloud.pets.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 需求表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-12-30
 */
@Service
public class DemandServiceImpl extends ServiceImpl<DemandMapper, Demand> implements DemandService {

    @Autowired
    private UserService userService;

    @Autowired
    private PetsSpeciesService speciesService;

    @Override
    public SpageUtil<Demand> listByPage(Map<String, Object> params, SpageUtil<Demand> spageUtil) {
        Wrapper<Demand> wrapper = new EntityWrapper<>();
        String typeId = "typeId";
        String categoryId = "categoryId";
        String speciesId = "speciesId";
        if (params.containsKey(typeId)) {
            wrapper.eq("type_id", params.get(typeId));
        }
        if (params.containsKey(categoryId)) {
            wrapper.eq("category_id", params.get(categoryId));
        }
        if (params.containsKey(speciesId)) {
            wrapper.eq("species_id", params.get(speciesId));
        }
        wrapper.ne("status", Constants.STAT_BLOCK);
        Page<Demand> page = new Page<>(spageUtil.getPage(), spageUtil.getStep());
        Page<Demand> circlePage = selectPage(page, wrapper);
        List<Demand> list = circlePage.getRecords();
        if (list != null && list.size() > 0) {
            spageUtil.setRows(list);
            int total = selectCount(wrapper);
            spageUtil.setTotal(total);
        }
        return spageUtil;
    }

    @Override
    public boolean add(Integer userId, Integer typeId, Integer age, Double price, Integer sex, Integer categoryId,
                       Integer speciesId, String content) {
        Demand demand = new Demand();
        demand.setUserId(userId);
        demand.setTypeId(typeId);
        demand.setAge(age);
        demand.setPrice(new BigDecimal(price));
        if (sex != null) {
            demand.setSex(sex);
        }
        demand.setCategoryId(categoryId);
        demand.setSpeciesId(speciesId);
        demand.setContent(content);
        return insert(demand);
    }

    @Override
    public DemandDto wrapper(Demand demand) {
        DemandDto demandDto = new DemandDto();
        if (demand == null) {
            return demandDto;
        }
        BeanUtils.copyProperties(demand, demandDto);
        Integer userId = demand.getUserId();
        Integer typeId = demand.getTypeId();
        Date pubTime = demand.getPubTime();
        User user = userService.selectById(userId);
        demandDto.setUsername(user.getNickname());
        // 宠物图片
        switch (typeId) {
            case 1:
                // 领养
                Integer speciesId = demand.getSpeciesId();
                if (speciesId != null) {
                    PetsSpecies species = speciesService.selectById(speciesId);
                    JSONObject json = JSON.parseObject(species.getImage());
                    demandDto.setImage(json.getString("url"));
                }
                break;
            case 2:
                // todo 获取宠物图片封面
                break;
            default:
                break;
        }
        demandDto.setPubTime(DateTimeUtil.leftTime(pubTime));
        return demandDto;
    }

    @Override
    public DemandDto getOne(Long id) {
        Demand demand = selectById(id);
        if (demand == null){
            return null;
        }
        String status = demand.getStatus();
        if (status.equals(Constants.STAT_BLOCK)){
            return null;
        }
        DemandDto demandDto;
        demandDto = wrapper(demand);
        return demandDto;
    }
}
