package com.cloud.base.util;

import cn.hutool.core.io.FileUtil;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;

/**
 * 七牛云工具类
 *
 * @Author: xujiping
 * @Date: 2020年1月5日 0005 下午 08:57:30
 * @Version 1.0
 */
@Slf4j
public class QiniuUtil {

    /**
     * 华南
     */
    private static Region region = Region.region2();
    private static String accessKey = "eUGhwzBCa0-_H8rr3bbGT6Ov_ry3eXBGL-1YWhFP";
    private static String secretKey = "T_sGxrouhk1SIH0t98D1Lil5_bJwDEpkN55f0qil";
    /**
     * bucket
     */
    private static String bucket = "dobi";

    /**
     * 文件上传
     *
     * @param inputStream
     * @param key         文件key，及对象存储的完整文件名；默认不指定key的情况下，以文件内容的hash值作为文件名
     * @return
     */
    public static String upload(InputStream inputStream, String key) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(region);
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        try {
            Response response = uploadManager.put(inputStream, key, upToken, null, null);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            key = putRet.key;
            log.info("文件key={}, hash={}", putRet.key, putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            log.error("文件上传失败,{}", r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
        return key;
    }

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Administrator\\Pictures\\timg.jpg");
        BufferedInputStream inputStream = FileUtil.getInputStream(file);
        upload(inputStream, "test/timg.jpg");
    }
}
