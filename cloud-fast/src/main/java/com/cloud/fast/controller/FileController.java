package com.cloud.fast.controller;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.cloud.auth.jwt.PassToken;
import com.cloud.base.constants.Constants;
import com.cloud.base.constants.ResultCode;
import com.cloud.base.constants.ReturnBean;
import com.cloud.base.exception.BusinessException;
import com.cloud.base.util.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author xujiping
 * @date 2019-08-02 11:02
 */
@Api(tags = "文件控制器")
@RestController
@RequestMapping("file")
public class FileController {

    @Value("${file.domain}")
    private String fileDomain;

    @Value("${file.server}")
    private String filePath;

    @PassToken
    @ApiOperation(value = "文件上传到本地", httpMethod = "POST")
    @PostMapping("upload/local")
    public String uploadLocal(@RequestParam("file") MultipartFile multipartFile, @RequestParam(required = false, defaultValue = "other") String folder) {
        // 文件上传，返回文件信息
        if (multipartFile == null) {
            throw new BusinessException(ResultCode.FAIL);
        }
        byte[] bytes;
        try {
            bytes = multipartFile.getBytes();
        } catch (IOException e) {
            throw new BusinessException(ResultCode.FAIL);
        }
        String originalFilename = multipartFile.getOriginalFilename();
        String suffix = StrUtil.subAfter(originalFilename, '.', true);
        String newName = IdUtil.fastSimpleUUID();
        String newFileName = newName + "." + suffix;
        File file = FileUtil.uploadFile(bytes, newFileName, filePath + folder);
        if (file == null) {
            throw new BusinessException(ResultCode.FAIL);
        }
        String url = fileDomain + folder + Constants.URL_SEPARATOR + newFileName;
        return new ReturnBean(url).toJson();
    }
}
