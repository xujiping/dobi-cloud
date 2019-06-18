package com.cloud.base.constants;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * @author xujiping
 * @date 2018/6/11 15:58
 */
public class ReturnBean {

    private int code;
    private String msg;
    private Object data;
    private Long count;
    private Long minTime;
    private Long maxTime;

    /**
     * 成功
     */
    public ReturnBean() {
        this.code = ReturnCode.SUCCESS.code();
        this.msg = ReturnCode.SUCCESS.msg();
    }

    public ReturnBean(Object data) {
        this.code = ReturnCode.SUCCESS.code();
        this.msg = ReturnCode.SUCCESS.msg();
        this.data = data;
    }

    public boolean isSuccess() {
        return code == ReturnCode.SUCCESS.code();
    }

    /**
     * 错误码
     *
     * @param code 错误码
     */
    public ReturnBean(ReturnCode code) {
        this.code = code.code();
        this.msg = code.msg();
    }

    public ReturnBean(ReturnCode code, Object data) {
        this.code = code.code();
        this.msg = code.msg();
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setReturnCode(ReturnCode returnCode, Object data) {
        this.code = returnCode.code();
        this.msg = returnCode.msg();
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
