package com.cloud.base.util;

/**
 * 请求工具类
 */
public class RequestUtil {

    /**
     * 判断user-agent类型是pc-web、phone-web、other
     *
     * @param userAgent
     * @return
     */
    public static String checkAgentType(String userAgent) {
        if (userAgent.contains("Windows")) {
            return "pc-web";
        }
        return "other";
    }
}
