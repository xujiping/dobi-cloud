package com.gateway.cloud.common;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 全局结果返回实体
 *
 * @author xujiping
 * @date 2018/6/20 13:40
 */
@Data
@AllArgsConstructor
public class Result implements Serializable {

    private Integer code;

    private String message;

    private Object data;

    public void setResultCode(ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.msg();
    }

    public Result() {
    }

    public Result(ResultCode resultCode, Object data) {
        this.code = resultCode.code();
        this.message = resultCode.msg();
        this.data = data;
    }


    public Boolean isSuccess(){
        return code == ResultCode.SUCCESS.code();
    }

    public static Result success() {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public static Result failure(ResultCode resultCode) {
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }

    public static Result failure(ResultCode resultCode, Object data) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    /**
     * 转换成json字符串
     *
     * @return
     */
    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("message", message);
        jsonObject.put("data", data);
        return jsonObject;
    }

}
