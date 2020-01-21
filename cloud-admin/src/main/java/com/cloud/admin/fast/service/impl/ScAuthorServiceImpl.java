package com.cloud.admin.fast.service.impl;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.admin.fast.entity.ScAuthor;
import com.cloud.admin.fast.mapper.ScAuthorMapper;
import com.cloud.admin.fast.service.ScAuthorService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.base.constants.ResultCode;
import com.cloud.base.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * <p>
 * 诗词-作者 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2020-01-18
 */
@Service
public class ScAuthorServiceImpl extends ServiceImpl<ScAuthorMapper, ScAuthor> implements ScAuthorService {

    @Override
    public Long json2Sc(String path) {
        long error = 0;
        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            throw new BusinessException(ResultCode.FAIL);
        }
        JSONArray jsonArray = JSONUtil.readJSONArray(file, CharsetUtil.CHARSET_UTF_8);
        JSONObject jsonObject;
        ScAuthor scAuthor;
        String name;
        if (jsonArray.size() > 0) {
            for (Object object : jsonArray) {
                jsonObject = (JSONObject) object;
                name = jsonObject.getStr("name");
                if (getByName(name) != null) {
                    continue;
                }
                scAuthor = new ScAuthor();
                scAuthor.setName(name);
                scAuthor.setDesc(jsonObject.getStr("desc"));
                scAuthor.setId(jsonObject.getStr("id"));
                scAuthor.setDynasty("唐");
                if (!insert(scAuthor)) {
                    error++;
                }
            }
        }
        return error;
    }

    @Override
    public ScAuthor getByName(String name) {
        if (StrUtil.isBlank(name)) {
            return null;
        }
        Wrapper<ScAuthor> wrapper = new EntityWrapper<>();
        wrapper.eq("name", name);
        return selectOne(wrapper);
    }

}
