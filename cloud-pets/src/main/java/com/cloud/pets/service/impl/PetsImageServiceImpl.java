package com.cloud.pets.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.pets.entity.PetsImage;
import com.cloud.pets.mapper.PetsImageMapper;
import com.cloud.pets.service.PetsImageService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 宠物图片表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-01-29
 */
@Service
public class PetsImageServiceImpl extends ServiceImpl<PetsImageMapper, PetsImage> implements PetsImageService {

    @Override
    public String getDefaultImage(long petsId) {
        Wrapper wrapper = new EntityWrapper();
        wrapper.eq("pets_id", petsId);
        wrapper.eq("default_image", true);
        PetsImage petsImage = selectOne(wrapper);
        if (petsImage != null) {
            return petsImage.getImage();
        }
        return null;
    }
}
