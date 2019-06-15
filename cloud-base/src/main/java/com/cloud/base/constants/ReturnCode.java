package com.cloud.base.constants;

/**
 * @author xujiping
 * @date 2018/6/11 15:59
 */
public enum ReturnCode {

    /**
     * 系统
     */
    SUCCESS(0, "成功"),
    FAIL(1, "操作失败，请检查后重试"),
    PARAMS_ERROR(100, "参数异常，请检查"),
    NOT_EXISTS(101, "目标不存在"),
    NO_TOKEN(102, "无token，请重新登录"),
    INVALID_REQUEST(111, "无效请求"),
    TOKEN_FAIL(401, "token认证失败，请重新登录"),

    /**
     * 用户
     */
    LOGIN_FAIL(1001, "登录失败，账号或密码错误"),
    USER_LOCKED(1003, "该账号已被锁定，请联系管理员"),
    USER_NOT_EXISTS(1005, "用户不存在"),
    USER_BLOCK(1007, "用户已冻结，请联系我们"),
    NEED_LOGIN(1009, "访问的服务需要身份认证，请引导用户到登录页"),
    USER_EXIST(1010, "用户已存在"),
    NO_PERMISSION(1012, "权限不足"),
    ROLE_EXIST(1014, "角色已存在"),
    ROLE_NULL_OR_UNENABLE(1016, "角色不存在或者不可用"),


    /**
     * 第三方
     */
    WX_CODE_SESSION_ERROR(1400, "微信code2Session失败"),

    /**
     * 宠物项目
     */
    PETS_NOT_EXISTS(2001, "不存在该宠物"),
    COLLECTED(2003, "已经收藏该资源")
    ;

    private int code;
    private String msg;

    ReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int code() {
        return code;
    }

    public String msg() {
        return msg;
    }
}
