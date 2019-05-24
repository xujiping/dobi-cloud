package com.cloud.pets.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.pets.entity.RemindType;
import com.cloud.pets.mapper.RemindTypeMapper;
import com.cloud.pets.service.RemindTypeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 提醒类型表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-12-30
 */
@Service
public class RemindTypeServiceImpl extends ServiceImpl<RemindTypeMapper, RemindType> implements RemindTypeService {

}
