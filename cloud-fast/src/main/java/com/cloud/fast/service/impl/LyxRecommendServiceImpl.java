package com.cloud.fast.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.base.constants.StatusEnum;
import com.cloud.fast.entity.LyxLabel;
import com.cloud.fast.entity.LyxRecommend;
import com.cloud.fast.entity.vo.LyxLabelVo;
import com.cloud.fast.entity.vo.LyxRecommendVo;
import com.cloud.fast.entity.vo.UserOpenInfoVo;
import com.cloud.fast.mapper.LyxRecommendMapper;
import com.cloud.fast.service.LyxLabelService;
import com.cloud.fast.service.LyxRecommendService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.cloud.fast.service.UserCenterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 推荐表 服务实现类
 * </p>
 *
 * @author xujiping
 * @since 2019-08-28
 */
@Service
public class LyxRecommendServiceImpl extends ServiceImpl<LyxRecommendMapper, LyxRecommend> implements LyxRecommendService {

    @Autowired
    private UserCenterService userCenterService;

    @Autowired
    private LyxLabelService lyxLabelService;

    @Override
    public LyxRecommend add(String userId, String title, String desc) {
        Date date = new Date();
        LyxRecommend recommend = new LyxRecommend();
        recommend.setTitle(title);
        recommend.setDesc(desc);
        recommend.setCreateBy(userId);
        recommend.setCreateTime(date);
        recommend.setUpdateTime(date);
        recommend.setStatus(StatusEnum.NORMAL.getCode());
        return insert(recommend) ? recommend : null;
    }

    @Override
    public Page<LyxRecommendVo> listByPage(Page<LyxRecommend> page) {
        Page<LyxRecommendVo> newPage = new Page<>(page.getCurrent(), page.getSize());
        Wrapper<LyxRecommend> wrapper = new EntityWrapper<>();
        wrapper.eq("status", StatusEnum.NORMAL.getCode());
        page = selectPage(page);
        List<LyxRecommendVo> list;
        List<LyxRecommend> records = page.getRecords();
        if (records != null && records.size() > 0) {
            list = records.stream().map(this::wrapper).collect(Collectors.toList());
            newPage.setRecords(list);
            newPage.setTotal(selectCount(wrapper));
        }
        return newPage;
    }

    @Override
    public LyxRecommendVo wrapper(LyxRecommend lyxRecommend) {
        LyxRecommendVo recommendVo = new LyxRecommendVo();
        if (lyxRecommend == null) {
            return recommendVo;
        }
        BeanUtils.copyProperties(lyxRecommend, recommendVo);
        String createBy = lyxRecommend.getCreateBy();
        // 发起人
        if (StrUtil.isNotBlank(createBy)) {
            UserOpenInfoVo openInfo = userCenterService.getOpenInfo(createBy);
            recommendVo.setCreateHeader(openInfo.getAvatar());
        }
        return recommendVo;
    }

    @Override
    public LyxRecommendVo wrapperDetail(LyxRecommend lyxRecommend, String userId) {
        LyxRecommendVo recommendVo = new LyxRecommendVo();
        if (lyxRecommend == null) {
            return recommendVo;
        }
        recommendVo = wrapper(lyxRecommend);
        String labelIds = lyxRecommend.getLabelIds();
        // 详情
        List<LyxLabelVo> results;
        List<LyxLabel> list = lyxLabelService.listByIds(labelIds);
        if (list != null && list.size() > 0) {
            results = list.stream().map(lyxLabel -> lyxLabelService.wrapper(lyxLabel, userId)).collect(Collectors.toList());
            recommendVo.setLabels(results);
        }
        return recommendVo;
    }

}
