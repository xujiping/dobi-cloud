package com.cloud.base.open.qiniu;

import com.qiniu.util.Auth;

/**
 * 七牛上传工具
 *
 * @Author: xujiping
 * @Date: 2019年8月22日 0022 下午 02:40:44
 * @Version 1.0
 */
public class QiniuUploadUtil {

    /**
     * 获取上传凭证
     * @param accessKey
     * @param secretKey
     * @param bucket
     * @return
     */
    public static String getUpToken(String accessKey, String secretKey, String bucket) {
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.uploadToken(bucket);
    }
}
