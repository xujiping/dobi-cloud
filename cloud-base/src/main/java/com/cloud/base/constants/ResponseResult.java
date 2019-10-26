package com.cloud.base.constants;

import java.lang.annotation.*;

/**
 * 包装返回结果注解类
 * @Author: xujiping
 * @Date: 2019年10月26日 0026 上午 09:31:45
 * @Version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface ResponseResult {
}
