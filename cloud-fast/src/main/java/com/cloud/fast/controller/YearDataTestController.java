package com.cloud.fast.controller;


import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.cloud.auth.jwt.PassToken;
import com.cloud.fast.entity.YearDataTest;
import com.cloud.fast.service.YearDataTestService;
import com.cloud.fast.util.IdCardUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2019-11-25
 */
@RestController
@RequestMapping("/yearDataTest")
public class YearDataTestController {

    @Autowired
    private YearDataTestService yearDataTestService;

    @PassToken
    @GetMapping("check")
    public String check() {
        List<YearDataTest> list = yearDataTestService.selectList(null);
        if (list != null && list.size() > 0) {
            String realName;
            String idCard;
            JSONObject check;
            for (YearDataTest yearDataTest : list) {
                if (yearDataTest.getAccurate() != null) {
                    continue;
                }
                realName = yearDataTest.getIdName();
                if (StrUtil.isBlank(realName)) {
                    realName = yearDataTest.getName();
                }
                idCard = yearDataTest.getIdCard();
                if (StrUtil.isBlank(idCard) || StrUtil.isBlank(realName)) {
                    yearDataTest.setAccurate(false);
                }
                check = IdCardUtil.check(realName, idCard);
                if (check == null || !check.getString("status").equals("01")) {
                    realName = yearDataTest.getName();
                    check = IdCardUtil.check(realName, idCard);
                    if (check == null || !check.getString("status").equals("01")) {
                        yearDataTest.setAccurate(false);
                    } else {
                        yearDataTest.setRealName(check.getString("name"));
                        yearDataTest.setAccurate(true);
                    }
                } else {
                    yearDataTest.setRealName(check.getString("name"));
                    yearDataTest.setAccurate(true);
                }
                yearDataTestService.updateById(yearDataTest);
            }
        }
        return "success";
    }

}

