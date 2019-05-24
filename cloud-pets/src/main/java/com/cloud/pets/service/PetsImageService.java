package com.cloud.pets.service;

import com.baomidou.mybatisplus.service.IService;
import com.cloud.pets.entity.PetsImage;

/**
 * <p>
 * 宠物图片表 服务类
 * </p>
 *
 * @author xujiping
 * @since 2019-01-29
 */
public interface PetsImageService extends IService<PetsImage> {

    /**
     * 获取宠物默认图片
     * @param petsId 宠物ID
     * @return 图片URL
     */
    String getDefaultImage(long petsId);

}
