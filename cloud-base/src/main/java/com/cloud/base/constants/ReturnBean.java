package com.cloud.base.constants;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author xujiping
 * @date 2018/6/11 15:58
 */
@Data
public class ReturnBean<T> {

    private int code;
    private String msg;
    private T data;
    private Long count;
    private Long minTime;
    private Long maxTime;

    /**
     * 成功
     */
    public ReturnBean() {
        this.code = ResultCode.SUCCESS.code();
        this.msg = ResultCode.SUCCESS.msg();
    }

    public ReturnBean(T data) {
        this.code = ResultCode.SUCCESS.code();
        this.msg = ResultCode.SUCCESS.msg();
        this.data = data;
    }

    public boolean isSuccess() {
        return code == ResultCode.SUCCESS.code();
    }

    /**
     * 错误码
     *
     * @param code 错误码
     */
    public ReturnBean(ResultCode code) {
        this.code = code.code();
        this.msg = code.msg();
    }

    public ReturnBean(ResultCode code, T data) {
        this.code = code.code();
        this.msg = code.msg();
        this.data = data;
    }

    public void setReturnCode(ResultCode resultCode, T data) {
        this.code = resultCode.code();
        this.msg = resultCode.msg();
        this.data = data;
    }

    public void setCodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Long getMinTime() {
        return minTime;
    }

    public void setMinTime(Long minTime) {
        this.minTime = minTime;
    }

    public Long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(Long maxTime) {
        this.maxTime = maxTime;
    }

    @Override
    public String toString() {
        return "ReturnBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", count=" + count +
                ", minTime=" + minTime +
                ", maxTime=" + maxTime +
                '}';
    }

    /**
     * 转换成json字符串
     *
     * @return
     */
    public String toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        jsonObject.put("data", data);
        jsonObject.put("count", count);
        jsonObject.put("minTime", minTime);
        jsonObject.put("maxTime", maxTime);
        return jsonObject.toJSONString();
    }

}
