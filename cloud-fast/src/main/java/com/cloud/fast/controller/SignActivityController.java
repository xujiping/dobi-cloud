package com.cloud.fast.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.cloud.auth.jwt.PassToken;
import com.cloud.auth.jwt.UserLoginToken;
import com.cloud.base.constants.Constants;
import com.cloud.base.constants.ResultCode;
import com.cloud.base.constants.ReturnBean;
import com.cloud.base.exception.BusinessException;
import com.cloud.fast.entity.SignActivity;
import com.cloud.fast.entity.SignForm;
import com.cloud.fast.entity.SignUserForm;
import com.cloud.fast.entity.dto.SignActivityDto;
import com.cloud.fast.entity.dto.SignFormDto;
import com.cloud.fast.entity.dto.SignUserFormDto;
import com.cloud.fast.entity.vo.SignActivityDetailVo;
import com.cloud.fast.entity.vo.UserApplyVo;
import com.cloud.fast.service.SignActivityService;
import com.cloud.fast.service.SignFormService;
import com.cloud.fast.service.SignUserFormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 活动报名表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2019-08-02
 */
@RestController
@RequestMapping("/signActivity")
@Api(tags = "活动报名")
public class SignActivityController {

    @Autowired
    private SignActivityService signActivityService;

    @Autowired
    private SignFormService signFormService;

    @Autowired
    private SignUserFormService signUserFormService;

    @UserLoginToken
    @ApiOperation(value = "新增活动", httpMethod = "POST")
    @PostMapping("add")
    public String add(HttpServletRequest request, @RequestBody SignActivityDto signActivityDto) {
        String key = request.getParameter(Constants.HEADER_ACCOUNT_ID);
        SignActivity signActivity = signActivityService.add(key, signActivityDto);
        if (signActivity == null) {
            throw new BusinessException(ResultCode.FAIL);
        }
        return new ReturnBean().toJson();
    }

    @UserLoginToken
    @ApiOperation(value = "详情", httpMethod = "GET", response = SignActivityDetailVo.class)
    @GetMapping("detail/{id}")
    public ReturnBean detail(HttpServletRequest request, @PathVariable String id) {
        String key = request.getParameter(Constants.HEADER_ACCOUNT_ID);
        SignActivity signActivity = signActivityService.getById(id);
        return new ReturnBean(signActivityService.wrapperDetail(signActivity, key));
    }

    @PassToken
    @ApiOperation(value = "列表", httpMethod = "GET")
    @GetMapping("list")
    public String list(@ApiParam(name = "page", value = "页码", defaultValue = "1")
                       @RequestParam(required = false, defaultValue = "1") Integer page,
                       @ApiParam(name = "size", value = "大小", defaultValue = "10")
                       @RequestParam(required = false, defaultValue = "10") Integer size,
                       @ApiParam(name = "latitude", value = "纬度")
                       @RequestParam(required = false) String latitude,
                       @ApiParam(name = "longitude", value = "经度")
                       @RequestParam(required = false) String longitude) {
        Page<SignActivity> pageObject = new Page<>(page, size);
        Map<String, Object> params = MapUtil.newHashMap(2);
        if (StrUtil.isNotBlank(latitude) && NumberUtil.isNumber(latitude)) {
            params.put("latitude", latitude);
        }
        if (StrUtil.isNotBlank(longitude) && NumberUtil.isNumber(longitude)) {
            params.put("latitude", longitude);
        }
        pageObject.setCondition(params);
        return new ReturnBean(signActivityService.listByPage(pageObject)).toJson();
    }

    @PassToken
    @ApiOperation(value = "获取表单ID", httpMethod = "POST")
    @PostMapping("formId")
    public String getFormId(@RequestBody SignFormDto signFormDto) {
        return new ReturnBean(signFormService.getFormId(signFormDto)).toJson();
    }

    @PassToken
    @ApiOperation(value = "获取表单信息", httpMethod = "GET", response = SignForm.class)
    @GetMapping("form/{id}")
    public ReturnBean getForm(@PathVariable Integer id) {
        if (id == null) {
            throw new BusinessException(ResultCode.PARAMS_ERROR);
        }
        return new ReturnBean(signFormService.selectById(id));
    }

    @UserLoginToken
    @ApiOperation(value = "参加", httpMethod = "POST")
    @PostMapping("join")
    public String join(HttpServletRequest request,
                       @RequestBody SignUserFormDto signUserFormDto) {
        String key = request.getParameter(Constants.HEADER_ACCOUNT_ID);
        signUserFormService.join(key, signUserFormDto);
        return new ReturnBean().toJson();
    }

    @UserLoginToken
    @ApiOperation(value = "用户参与/发布的列表", httpMethod = "GET", response = UserApplyVo.class)
    @GetMapping("user/applys")
    public ReturnBean userApplys(HttpServletRequest request,
                                 @ApiParam(name = "type", value = "类型：1我参加的 2我发布的")
                                 @RequestParam(required = false, defaultValue = "1") Integer type) {
        List<UserApplyVo> list;
        Collection<UserApplyVo> userApplyVos = null;
        String key = request.getParameter(Constants.HEADER_ACCOUNT_ID);
        if (type == 1) {
            // 我参与的
            List<SignUserForm> userActivities = signUserFormService.listByUser(key);
            if (userActivities != null && userActivities.size() > 0) {
                list = userActivities.stream().map(signUserForm -> signUserFormService.wrapper(signUserForm)).collect(Collectors.toList());
                userApplyVos = CollectionUtil.removeNull(list);
            }
        } else {
            // 我发布的
            List<SignActivity> signActivities = signActivityService.listByUser(key);
            if (signActivities != null && signActivities.size() > 0) {
                list = signActivities.stream().map(signActivity -> signActivityService.wrapperToApply(signActivity)).collect(Collectors.toList());
                userApplyVos = CollectionUtil.removeNull(list);
            }
        }
        return new ReturnBean(userApplyVos);
    }

}

