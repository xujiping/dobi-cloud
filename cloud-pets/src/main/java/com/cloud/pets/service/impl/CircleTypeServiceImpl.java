package com.cloud.pets.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.base.constants.Constants;
import com.cloud.pets.entity.CircleType;
import com.cloud.pets.mapper.CircleTypeMapper;
import com.cloud.pets.service.CircleTypeService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 圈子类型 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-12-29
 */
@Service
public class CircleTypeServiceImpl extends ServiceImpl<CircleTypeMapper, CircleType> implements CircleTypeService {

    @Override
    public List<CircleType> getAll() {
        Wrapper<CircleType> wrapper = new EntityWrapper<>();
        wrapper.eq("status", Constants.STAT_NORMAL);
        return selectList(wrapper);
    }
}
