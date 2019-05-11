package com.cloud.base.constants;

/**
 * @author xujiping
 * @date 2018/6/11 15:59
 */
public enum ReturnCode {

    /**
     * 成功
     */
    SUCCESS(0, "成功"),
    FAIL(1, "操作失败，请检查后重试"),
    SERVICE_UENABLE(2, "服务不可用，请稍后重试"),
    LOGIN_FAIL(3, "登录失败，账号或密码错误"),
    USER_LOCKED(5, "该账号已被锁定，请联系管理员"),
    NOT_EXISTS(6, "目标不存在"),
    USER_NOT_EXISTS(8, "用户不存在"),
    USER_BLOCK(10, "用户已冻结，请联系我们"),
    NEED_LOGIN(12, "访问的服务需要身份认证，请引导用户到登录页"),
    REQUEST_NOT_EXISTS(400, "请求不存在"),
    SERVER_ERROR(500, "服务器错误"),
    PARAMS_ERROR(600, "参数异常，请检查"),
    NULL_INFO(1001, "相关信息为空"),
    GOODS_CATEGORY_NULL(1002, "商品类别不存在"),
    SELLER_NULL(1004, "卖家不存在"),
    GOODS_NULL(1006, "商品不存在"),
    SKU_NULL(1010, "库存不足"),
    LISTEN_USER_NOT_EXISTS(1012, "收听用户不存在"),
    FILE_UPLOAD_FAIL(1014, "文件上传失败"),
    RELEASE_NOT_EXIST(1016, "不存在该专辑"),
    VOICE_FILE_ERROR(1018, "音频文件错误，上传失败"),
    LISTEN_USER_EXISTS(1014, "用户已存在"),
    VALIDATE_CODE_ERROR(1016, "短信验证码不正确"),
    REPEAT_PASSWORD_ERROR(1018, "两次输入的密码不一致"),
    SMS_SEND_ERROR(1020, "短线验证码发送失败，请稍后重试"),
    ATLAS_EXIST(1022, "标题已存在，请修改后重试"),
    CODE_EXIST(1024, "邀请码已经存在"),
    RELEASE_NO_PASS(1026, "专辑还未通过，请先通过专辑"),

    PETS_NOT_EXISTS(2000, "不存在该宠物"),

    UPDATE_FAIL(5000, "更新失败，请检查数据后重试"),
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
