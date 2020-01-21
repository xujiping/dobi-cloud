package com.cloud.admin.fast.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.cloud.admin.fast.entity.ScAuthor;
import com.cloud.admin.fast.entity.ScPoetry;
import com.cloud.admin.fast.entity.vo.ScPoetryVo;
import com.cloud.admin.fast.mapper.ScPoetryMapper;
import com.cloud.admin.fast.service.ScPoetryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.base.constants.ResultCode;
import com.cloud.base.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 诗词 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2020-01-18
 */
@Service
public class ScPoetryServiceImpl extends ServiceImpl<ScPoetryMapper, ScPoetry> implements ScPoetryService {

    @Override
    public Long json2Sc(String path) {
        long error = 0;
        File file = new File(path);
        if (!file.exists() || !file.isFile()) {
            throw new BusinessException(ResultCode.FAIL);
        }
        JSONArray jsonArray = JSONUtil.readJSONArray(file, CharsetUtil.CHARSET_UTF_8);
        JSONObject jsonObject;
        ScPoetry scPoetry;
        String id;
        Object paragraphs;
        Object tags;
        if (jsonArray.size() > 0) {
            for (Object object : jsonArray) {
                jsonObject = (JSONObject) object;
                id = jsonObject.getStr("id");
                if (selectById(id) != null) {
                    continue;
                }
                paragraphs = jsonObject.get("paragraphs");
                tags = jsonObject.get("tags");
                scPoetry = new ScPoetry();
                scPoetry.setTitle(jsonObject.getStr("title"));
                scPoetry.setAuthor(jsonObject.getStr("author"));
                if (paragraphs != null) {
                    scPoetry.setParagraphs(paragraphs.toString());
                }
                scPoetry.setId(jsonObject.getStr("id"));
                scPoetry.setChapter(jsonObject.getStr("chapter"));
                scPoetry.setSection(jsonObject.getStr("section"));
                scPoetry.setRhythmic(jsonObject.getStr("rhythmic"));
                scPoetry.setNotes(jsonObject.getStr("notes"));
                if (tags != null) {
                    scPoetry.setTags(tags.toString());
                }
                scPoetry.setDynasty("宋");
                scPoetry.setType("诗");
                if (!insert(scPoetry)) {
                    error++;
                }
            }
        }
        return error;
    }

    @Override
    public List<ScPoetry> getByTitle(String title) {
        Wrapper<ScPoetry> wrapper = new EntityWrapper<>();
        wrapper.eq("title", title);
        return selectList(wrapper);
    }

    @Override
    public ScPoetryVo wrapper(ScPoetry poetry) {
        ScPoetryVo poetryVo = new ScPoetryVo();
        if (poetry == null){
            return poetryVo;
        }
        BeanUtils.copyProperties(poetry, poetryVo);
        String paragraphs = poetry.getParagraphs();
        List<String> paragraphsList = new ArrayList<>();
        if (StrUtil.isNotBlank(paragraphs)){
            String[] split = StrUtil.split(paragraphs, "\",\"");
            for (String str : split) {
                paragraphsList.add(StrUtil.removeAll(str, '[', ']', '"'));
            }
            poetryVo.setParagraphs(paragraphsList);
        }
        return poetryVo;
    }

}
