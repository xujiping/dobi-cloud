package com.cloud.pets.controller;


import com.cloud.base.constants.ReturnBean;
import com.cloud.base.constants.ReturnCode;
import com.cloud.pets.service.CollectService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 收藏表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2019-01-28
 */
@Controller
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    @ApiOperation(value = "收藏", httpMethod = "POST", response = ReturnBean.class, notes = "收藏资源")
    @PostMapping("")
    public String collect(
            @ApiParam(required = true, name = "key", value = "用户ID")
            @RequestHeader(value = "key") String key,
            @ApiParam(required = true, name = "subject", value = "主题：circle圈子，demand需求")
            @RequestParam String subject,
            @ApiParam(required = true, name = "id", value = "资源ID")
            @RequestParam Long id) {
        ReturnBean rb = new ReturnBean();
        boolean add = collectService.add(key, subject, id);
        if (!add) {
            rb.setReturnCode(ReturnCode.FAIL, null);
        }
        return rb.toJsonString();
    }

}

