package com.cloud.admin.fast.util;

import cn.hutool.core.util.StrUtil;

/**
 * @Author: xujiping
 * @Date: 2020年1月5日 0005 下午 10:30:40
 * @Version 1.0
 */
public class BookUtil {

    /**
     * 设置图片宽度为100%
     * @param content
     * @return
     */
    public static String setImg(String content){
       return StrUtil.replace(content, "<img", "<img style='width:100%;'");
    }
}
