package com.cloud.admin.fast.controller;

import com.cloud.auth.jwt.PassToken;
import com.cloud.base.constants.ResponseResult;
import com.cloud.base.util.QiniuUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: xujiping
 * @Date: 2020年1月5日 0005 下午 08:50:24
 * @Version 1.0
 */
@RestController
@RequestMapping("file")
@ResponseResult
@Api(tags = "文件控制器")
public class FileController {

    @Value("${file.domain}")
    private String fileDomain;

    @ApiOperation(value = "文件上传")
    @PassToken
    @PostMapping("upload")
    public String upload(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()){
            return null;
        }
        return fileDomain + QiniuUtil.upload(file.getInputStream(), null);
    }
}
