package com.cloud.pets.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.pets.entity.Party;
import com.cloud.pets.mapper.PartyMapper;
import com.cloud.pets.service.PartyService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 聚会表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-12-30
 */
@Service
public class PartyServiceImpl extends ServiceImpl<PartyMapper, Party> implements PartyService {

}
