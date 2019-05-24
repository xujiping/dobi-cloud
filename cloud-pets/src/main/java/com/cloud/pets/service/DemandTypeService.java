package com.cloud.pets.service;

import com.baomidou.mybatisplus.service.IService;
import com.cloud.pets.entity.DemandType;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-12-30
 */
public interface DemandTypeService extends IService<DemandType> {

    /**
     * 查询所有列表
     * @return list
     */
    List<DemandType> getAll();
}
