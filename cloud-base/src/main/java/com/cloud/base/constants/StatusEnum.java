package com.cloud.base.constants;

/**
 * 状态枚举
 * @Author: xujiping
 * @Date: 2019年8月18日 0018 下午 05:23:02
 * @Version 1.0
 */
public enum StatusEnum {

    /**
     * 通用
     */
    UNABLE(0, "不可用"),
    NORMAL(1, "正常"),
    /**
     * 审核，10-19
     */
    CHECK_PRE(10, "待审核"),
    /**
     * 订单，20-29
     */
    ORDER_PAY_PRE(20, "待支付"),
    /**
     * 活动报名，30-39
     */
    APPLY_FULL(30, "报名已满"),
    ;

    private int code;
    private String name;

    StatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

}
