package com.cloud.base.exception;

import com.cloud.base.constants.ResultCode;

/**
 * 业务异常
 *
 * @author xujiping
 * @date 2018/6/14 16:38
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -6118010137349240796L;

    /**
     * 错误码
     */
    private Integer code;

    private Object data;

    public BusinessException() {
        super(ResultCode.FAIL.msg());
        this.code = ResultCode.FAIL.code();
    }

    public BusinessException(ResultCode resultCode) {
        super(resultCode.msg());
        this.code = resultCode.code();
    }

    public BusinessException(ResultCode resultCode, Object data) {
        super(resultCode.msg());
        this.code = resultCode.code();
        this.data = data;
    }

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public BusinessException(ResultCode resultCode, Exception e) {
        super(resultCode.msg(), e.getCause());
        this.code = resultCode.code();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
