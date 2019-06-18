package com.cloud.pets.service;

import com.baomidou.mybatisplus.service.IService;
import com.cloud.pets.entity.PetsCategory;

import java.util.List;

/**
 * <p>
 * 宠物类别表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-12-30
 */
public interface PetsCategoryService extends IService<PetsCategory> {

    /**
     * 查询所有的宠物类别
     * @return list
     */
    List<PetsCategory> getAll();

    /**
     * 查询
     * @param name 类别名称
     * @return PetsCategory
     */
    PetsCategory get(String name);

    /**
     * 新增类别
     * @param name 类别名称
     * @return boolean
     */
    boolean add(String name);

    /**
     * 更新
     * @param id id
     * @param name 名称
     * @param status 状态
     * @return boolean
     */
    boolean update(Integer id, String name, String status);

}
