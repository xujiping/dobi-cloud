package com.cloud.fast.controller;

import com.cloud.base.constants.ReturnBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xujiping
 * @date 2019-08-02 11:02
 */
@Api(tags = "文件控制器")
@RestController
@RequestMapping("file")
public class FileController {

    @ApiOperation(value = "文件上传到本地", httpMethod = "POST")
    @PostMapping("upload/local")
    public String uploadLocal(MultipartFile multipartFile) {
        // todo 文件上传，返回文件信息
        return new ReturnBean().toJson();
    }
}
