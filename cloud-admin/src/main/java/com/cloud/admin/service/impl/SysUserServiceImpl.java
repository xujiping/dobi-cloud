package com.cloud.admin.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cloud.admin.entity.SysUser;
import com.cloud.admin.entity.vo.UserVo;
import com.cloud.admin.jwt.JwtUtil;
import com.cloud.admin.mapper.SysUserMapper;
import com.cloud.admin.service.SysUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.base.constants.ReturnCode;
import com.cloud.base.exception.BusinessException;
import com.cloud.base.util.MD5Util;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 平台用户 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-06-05
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public SysUser getByUsername(String username) {
        Wrapper<SysUser> wrapper = new EntityWrapper<>();
        wrapper.eq("username", username);
        return selectOne(wrapper);
    }

    @Override
    public SysUser addNormal(String username, String password) {
        SysUser user = new SysUser();
        user.setId(IdUtil.fastSimpleUUID());
        user.setUsername(username);
        user.setPassword(MD5Util.MD5(password));
        return insert(user) ? user : null;
    }

    @Override
    public UserVo login(String username, String password) {
        SysUser user = getByUsername(username);
        if (user == null) {
            throw new BusinessException(ReturnCode.USER_NOT_EXISTS);
        }
        if (MD5Util.MD5(password).equals(user.getPassword())) {
            String token = JwtUtil.getToken(user);
            return new UserVo(user.getId(), token);
        }
        return null;
    }
}
