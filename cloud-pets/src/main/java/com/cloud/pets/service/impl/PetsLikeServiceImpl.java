package com.cloud.pets.service.impl;

import com.cloud.pets.entity.PetsLike;
import com.cloud.pets.mapper.PetsLikeMapper;
import com.cloud.pets.service.PetsLikeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 点赞表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-06-24
 */
@Service
public class PetsLikeServiceImpl extends ServiceImpl<PetsLikeMapper, PetsLike> implements PetsLikeService {

}
