package com.cloud.pets.controller;

import com.cloud.base.constants.Constants;
import com.cloud.base.constants.ReturnBean;
import com.cloud.base.constants.ReturnCode;
import com.cloud.base.exception.BusinessException;
import com.cloud.pets.entity.User;
import com.cloud.pets.service.FileService;
import com.cloud.pets.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author xujiping
 * @since 2018-12-29
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户")
public class UserController {

    @Value("${file.server}")
    private String fileServer;

    @Autowired
    private UserService userService;

    @Autowired private FileService fileService;

    @ApiOperation(value = "获取用户信息", httpMethod = "GET", response = User.class, notes = "获取用户信息，冻结用户除外")
    @GetMapping("info")
    public String info(@RequestHeader String key) {
        ReturnBean rb = new ReturnBean();
        User user = userService.selectById(key);
        if (user != null) {
            String status = user.getStatus();
            if (!status.equals(Constants.STAT_BLOCK)) {
                rb.setData(user);
            }
        }
        return rb.toJson();
    }

    @ApiOperation(value = "更新用户信息", httpMethod = "POST", response = ReturnBean.class, notes = "更新用户信息")
    @PostMapping("info")
    public String update(@RequestHeader String key,
                         @ApiParam(name = "nickname", value = "昵称")
                         @RequestParam(required = false)String nickname,
                         @ApiParam(name = "age", value = "年龄")
                         @RequestParam(required = false)Integer age,
                         @ApiParam(name = "sex", value = "性别")
                         @RequestParam(required = false)Integer sex,
                         @ApiParam(name = "phone", value = "手机号")
                         @RequestParam(required = false)String phone,
                         @ApiParam(name = "address", value = "地址")
                         @RequestParam(required = false)String address){
        ReturnBean rb = new ReturnBean();
        boolean update = userService.update(Long.valueOf(key), nickname, age, sex, phone, address);
        if (!update){
            throw new BusinessException(ReturnCode.FAIL);
        }
        return rb.toJson();
    }

    @PostMapping("head")
    public String upload(@RequestParam(value = "file")MultipartFile file){
        ReturnBean rb = new ReturnBean();
        String fileName = file.getOriginalFilename();
        String filePath = fileServer + "head";
        boolean upload = fileService.uploadMultipartFile(file, "head", fileName, filePath);
        if (!upload){
            throw new BusinessException(ReturnCode.FAIL);
        }
        return rb.toJson();
    }


}

