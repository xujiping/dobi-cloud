package com.cloud.admin.fast.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.admin.fast.entity.GjToday;
import com.cloud.admin.fast.entity.vo.TodayVo;
import com.cloud.admin.fast.mapper.GjTodayMapper;
import com.cloud.admin.fast.service.GjTodayService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 每日推送 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-12-26
 */
@Service
public class GjTodayServiceImpl extends ServiceImpl<GjTodayMapper, GjToday> implements GjTodayService {

    @Override
    public Page<TodayVo> page(Page<GjToday> pageObject) {
        Wrapper<GjToday> wrapper = new EntityWrapper<>();
        wrapper.orderBy("create_tiome", false);
        pageObject = selectPage(pageObject);
        Page<TodayVo> page = new Page<>(pageObject.getCurrent(), pageObject.getSize());
        List<GjToday> records = pageObject.getRecords();
        if (records != null && records.size() > 0) {
            List<TodayVo> list = records.stream().map(this::wrapper).collect(Collectors.toList());
            page.setRecords(list);
        }
        return page;
    }

    @Override
    public TodayVo wrapper(GjToday gjToday) {
        TodayVo todayVo = new TodayVo();
        if (gjToday == null){
            return todayVo;
        }
        BeanUtils.copyProperties(gjToday, todayVo);
        String contents = gjToday.getContents();
        Date createTime = gjToday.getCreateTime();
        String book = gjToday.getBook();
        if (StrUtil.isNotBlank(book)){
            todayVo.setBook("｜︽ " + book + " ︾ ");
        }
        if (createTime != null){
            todayVo.setCreateTime(DateUtil.formatDate(createTime));
        }
        if (StrUtil.isNotBlank(contents)){
            List<String> list = new ArrayList<>();
            JSONArray objects = JSONArray.parseArray(contents);
            for (int i = 0; i < objects.size(); i++) {
                JSONObject object = (JSONObject) objects.get(i);
                list.add(object.getString("text"));
            }
            todayVo.setContents(list);
        }
        return todayVo;
    }
}
