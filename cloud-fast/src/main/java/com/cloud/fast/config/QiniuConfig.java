package com.cloud.fast.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: xujiping
 * @Date: 2019年8月22日 0022 下午 02:45:43
 * @Version 1.0
 */
@Configuration
@Data
public class QiniuConfig {

    @Value("${qiniu.access-key}")
    private String accessKey;

    @Value("${qiniu.secret-key}")
    private String secretKey;

    @Value("${qiniu.bucket}")
    private String bucket;

}
