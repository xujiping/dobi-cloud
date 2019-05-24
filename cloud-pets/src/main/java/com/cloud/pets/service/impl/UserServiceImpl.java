package com.cloud.pets.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.base.constants.Constants;
import com.cloud.base.constants.ReturnCode;
import com.cloud.base.exception.BusinessException;
import com.cloud.pets.entity.User;
import com.cloud.pets.mapper.UserMapper;
import com.cloud.pets.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2018-12-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User get(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        Wrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("username", username);
        return selectOne(wrapper);
    }

    @Override
    public boolean update(Long id, String nickname, Integer age, Integer sex, String phone, String address) {
        User user = selectById(id);
        if (user == null) {
            throw new BusinessException(ReturnCode.USER_NOT_EXISTS);
        }
        String status = user.getStatus();
        if (status.equals(Constants.STAT_BLOCK)) {
            throw new BusinessException(ReturnCode.USER_BLOCK);
        }
        if (StringUtils.isNotEmpty(nickname)) {
            user.setNickname(nickname);
        }
        if (age != null) {
            user.setAge(age);
        }
        if (sex != null) {
            user.setSex(sex);
        }
        if (StringUtils.isNotEmpty(phone)) {
            user.setPhone(phone);
        }
        if (StringUtils.isNotEmpty(address)) {
            user.setAddress(address);
        }
        return updateById(user);
    }
}
