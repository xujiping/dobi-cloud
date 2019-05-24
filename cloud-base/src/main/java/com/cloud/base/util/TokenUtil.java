package com.cloud.base.util;

/**
 * token工具类
 * @author xujiping
 * @date 2018/5/17 9:53
 */
public class TokenUtil {

    private static String SALT = "test987";

    /**
     * 生成token
     * @param clientListenId
     * @param timestamp
     * @return
     */
    public static String generate(String clientListenId, long timestamp){
        String str = clientListenId + "#" + timestamp + "#" + SALT;
        String encode = Base64Util.encode(str);
        String token = MD5Util.MD5(encode);
        return token;
    }

    public static void main(String[] args) {
        System.out.println(generate("sdsdfsdf", 11111111));
    }
}
