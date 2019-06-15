package com.cloud.pets.service;

import com.baomidou.mybatisplus.service.IService;
import com.cloud.base.util.SpageUtil;
import com.cloud.pets.entity.Pets;
import com.cloud.pets.entity.vo.PetsVo;

import java.util.Map;

/**
 * <p>
 * 宠物表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2018-12-30
 */
public interface PetsService extends IService<Pets> {

    /**
     * 分页查询
     * @param params 参数
     * @param spageUtil 分页工具类
     * @return list
     */
    SpageUtil<Pets> listByPage(Map<String, Object> params, SpageUtil<Pets> spageUtil);

    /**
     * 新增
     * @param key 用户ID
     * @param categoryId 类别ID
     * @param speciesId 品种ID
     * @param nickname 昵称
     * @param age 年龄
     * @param sex 性别
     * @return
     */
    boolean add(String key, Integer categoryId, Integer speciesId, String nickname, Integer age, Integer sex);

    /**
     * 更新
     * @param id 宠物ID
     * @param categoryId 类别ID
     * @param speciesId 品种ID
     * @param nickname 昵称
     * @param age 年龄
     * @param sex 性别
     * @return
     */
    boolean updateInfo(Long id, Integer categoryId, Integer speciesId, String nickname, Integer age, Integer sex);

    /**
     * 查询
     * @param key 用户ID
     * @param id 宠物ID
     * @return
     */
    Pets get(String key, Long id);

    /**
     * 封住那个
     * @param pets pets
     * @return petsDto
     */
    PetsVo wrapper(Pets pets);
}
