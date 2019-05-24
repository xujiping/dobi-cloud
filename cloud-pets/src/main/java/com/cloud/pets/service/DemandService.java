package com.cloud.pets.service;

import com.baomidou.mybatisplus.service.IService;
import com.cloud.base.util.SpageUtil;
import com.cloud.pets.entity.Demand;
import com.cloud.pets.entity.dto.DemandDto;

import java.util.Map;

/**
 * <p>
 * 需求表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-12-30
 */
public interface DemandService extends IService<Demand> {

    /**
     * 分页查询
     * @param params 参数
     * @param spageUtil 分页
     * @return 列表
     */
    SpageUtil<Demand> listByPage(Map<String, Object> params, SpageUtil<Demand> spageUtil);

    /**
     * 发布需求
     * @param userId 用户ID
     * @param typeId 类型ID
     * @param age 年龄
     * @param price 价格
     * @param sex 性别
     * @param categoryId 类别ID
     * @param speciesId 品种ID
     * @param content 备注
     * @return boolean
     */
    boolean add(Integer userId, Integer typeId, Integer age, Double price, Integer sex, Integer categoryId, Integer speciesId, String content);

    /**
     * 封装
     * @param demand demand
     * @return DemandDto
     */
    DemandDto wrapper(Demand demand);

    /**
     * 查询
     * @param id ID
     * @return
     */
    DemandDto getOne(Long id);
}
