package com.cloud.pets.service;

import com.baomidou.mybatisplus.service.IService;
import com.cloud.base.util.SpageUtil;
import com.cloud.pets.entity.Circle;
import com.cloud.pets.entity.vo.CircleVo;

import java.util.Map;

/**
 * <p>
 * 圈子 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-12-29
 */
public interface CircleService extends IService<Circle> {

    /**
     * 分页查询
     * @param params 参数
     * @param spageUtil 分页
     * @return 列表
     */
    SpageUtil<Circle> listByPage(Map<String, Object> params, SpageUtil<Circle> spageUtil);

    /**
     * 封装
     * @param circle
     * @return
     */
    CircleVo wrapper(Circle circle);

    /**
     * 查询
     * @param id ID
     * @return
     */
    CircleVo getOne(Long id);
}
