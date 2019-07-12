package com.cloud.pets.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.cloud.pets.entity.PetsSpecies;
import com.cloud.pets.entity.vo.PetsSpeciesVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 宠物品种表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-12-30
 */
public interface PetsSpeciesService extends IService<PetsSpecies> {

    /**
     * 查询所有
     * @return list
     */
    List<PetsSpecies> getAll();

    /**
     * 分页查询
     * @param page
     * @param params
     * @return
     */
    Page<PetsSpecies> list(Page<PetsSpecies> page, Map<String, Object> params);

    /**
     * 根据名字查询
     * @param name
     * @return
     */
    PetsSpecies get(String name);

    /**
     * 新增
     * @param name 名称
     * @param categoryId 类别ID
     * @param heat 发情期天数
     * @return boolean
     */
    boolean add(String name, Integer categoryId, Integer heat);

    /**
     * 更新
     * @param id id
     * @param name 名称
     * @param categoryId 类别ID
     * @param heat 发情期天数
     * @param status 状态
     * @return boolean
     */
    boolean update(Integer id, String name, Integer categoryId, Integer heat, String status);

    /**
     * 封装
     * @param petsSpecies
     * @return
     */
    PetsSpeciesVo wrapper(PetsSpecies petsSpecies);
}
