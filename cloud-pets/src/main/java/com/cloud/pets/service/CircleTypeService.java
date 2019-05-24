package com.cloud.pets.service;

import com.baomidou.mybatisplus.service.IService;
import com.cloud.pets.entity.CircleType;

import java.util.List;

/**
 * <p>
 * 圈子类型 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-12-29
 */
public interface CircleTypeService extends IService<CircleType> {

    /**
     * 所有的圈子类型列表
     * @return list
     */
    List<CircleType> getAll();

}
