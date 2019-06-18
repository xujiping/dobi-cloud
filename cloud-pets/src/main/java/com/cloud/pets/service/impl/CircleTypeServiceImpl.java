package com.cloud.pets.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.base.constants.Constants;
import com.cloud.pets.entity.CircleType;
import com.cloud.pets.entity.vo.CircleTypeVo;
import com.cloud.pets.mapper.CircleTypeMapper;
import com.cloud.pets.service.CircleTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
    public List<CircleTypeVo> getAll() {
        List<CircleTypeVo> circleTypeVos = new ArrayList<>();
        Wrapper<CircleType> wrapper = new EntityWrapper<>();
        wrapper.eq("status", Constants.STAT_NORMAL);
        List<CircleType> circleTypes = selectList(wrapper);
        if (circleTypes != null && circleTypes.size() > 0) {
            circleTypeVos = circleTypes.stream().map(this::wrapper).collect(Collectors.toList());
        }
        return circleTypeVos;
    }

    @Override
    public CircleTypeVo wrapper(CircleType circleType) {
        CircleTypeVo circleTypeVo = new CircleTypeVo();
        if (circleType == null) {
            return circleTypeVo;
        }
        BeanUtils.copyProperties(circleType, circleTypeVo);
        return circleTypeVo;
    }

}
