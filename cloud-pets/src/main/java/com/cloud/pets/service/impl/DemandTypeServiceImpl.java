package com.cloud.pets.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.base.constants.Constants;
import com.cloud.pets.entity.DemandType;
import com.cloud.pets.mapper.DemandTypeMapper;
import com.cloud.pets.service.DemandTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-12-30
 */
@Service
public class DemandTypeServiceImpl extends ServiceImpl<DemandTypeMapper, DemandType> implements DemandTypeService {

    @Override
    public List<DemandType> getAll() {
        Wrapper<DemandType> wrapper = new EntityWrapper<>();
        wrapper.eq("status", Constants.STAT_NORMAL);
        return selectList(wrapper);
    }
}
