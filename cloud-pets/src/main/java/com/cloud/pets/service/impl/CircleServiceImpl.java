package com.cloud.pets.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.base.constants.Constants;
import com.cloud.base.util.SpageUtil;
import com.cloud.pets.entity.Circle;
import com.cloud.pets.entity.vo.CircleVo;
import com.cloud.pets.mapper.CircleMapper;
import com.cloud.pets.service.CircleService;
import com.cloud.pets.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 圈子 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-12-29
 */
@Service
public class CircleServiceImpl extends ServiceImpl<CircleMapper, Circle> implements CircleService {

    @Autowired
    private UserService userService;

    @Override
    public SpageUtil<Circle> listByPage(Map<String, Object> params, SpageUtil<Circle> spageUtil) {
        Wrapper<Circle> wrapper = new EntityWrapper<>();
        wrapper.orderBy("create_time desc");
        String timeStr = "time";
        String beforeStr = "before";
        String circleTypeId = "circleTypeId";
        if (params.containsKey(timeStr)) {
            long time = (long) params.get(timeStr);
            Date date = new Date(time);
            boolean before = (boolean) params.get(beforeStr);
            if (params.containsKey(beforeStr)) {
                if (before) {
                    wrapper.lt("create_time", date);
                } else {
                    wrapper.gt("create_time", date);
                }
            }
        }
        if (params.containsKey(circleTypeId)) {
            wrapper.eq("type_id", params.get(circleTypeId));
        }
        wrapper.ne("status", Constants.STAT_BLOCK);
        Page<Circle> page = new Page<>(spageUtil.getPage(), spageUtil.getStep());
        Page<Circle> circlePage = selectPage(page, wrapper);
        List<Circle> list = circlePage.getRecords();
        if (list != null && list.size() > 0) {
            spageUtil.setRows(list);
            int total = selectCount(wrapper);
            spageUtil.setTotal(total);
        }
        return spageUtil;
    }

    @Override
    public CircleVo wrapper(Circle circle) {
        CircleVo circleVo = new CircleVo();
        if (circle == null) {
            return circleVo;
        }
        BeanUtils.copyProperties(circle, circleVo);
        // todo 发布人
        return circleVo;
    }

    @Override
    public CircleVo getOne(Long id) {
        if (id == null) {
            return null;
        }
        CircleVo circleVo = new CircleVo();
        Circle circle = selectById(id);
        if (circle == null) {
            return null;
        }
        String status = circle.getStatus();
        if (status.equals(Constants.STAT_BLOCK)) {
            return null;
        }
        BeanUtils.copyProperties(circle, circleVo);
        // todo 发布人
        return circleVo;
    }
}
