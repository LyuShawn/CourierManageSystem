package com.CourierManageSystem.backend.util;

import lombok.Data;


@Data
public class ResponseWrapper {
    /**
     * 状态
     */
    private String status;
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;


    public static ResponseWrapper markSuccess(String message, Object data) {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setStatus("success");
        responseWrapper.setCode(200);
        responseWrapper.setMessage(message);
        responseWrapper.setData(data);
        return responseWrapper;
    }

    /**
     *
     *
     * @param data 需要返回的对象
     * @return ResponseWrapper封装类
     */
    public static ResponseWrapper markError(String message, Object data) {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setStatus("error");
        responseWrapper.setCode(-1);
        responseWrapper.setMessage(message);
        responseWrapper.setData(data);
        return responseWrapper;
    }

    /**
     *
     *
     * @param message 信息
     * @return
     */
    public static ResponseWrapper markSuccess(String message) {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setStatus("success");
        responseWrapper.setCode(200);
        responseWrapper.setMessage(message);
        return responseWrapper;
    }

    /**
     *
     * @param message
     * @return
     */
    public static ResponseWrapper markError(String message) {
        ResponseWrapper responseWrapper = new ResponseWrapper();
        responseWrapper.setStatus("error");
        responseWrapper.setCode(-1);
        responseWrapper.setMessage(message);
        return responseWrapper;
    }


}
