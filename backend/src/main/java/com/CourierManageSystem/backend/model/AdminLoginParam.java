package com.CourierManageSystem.backend.model;

import lombok.Data;

@Data
public class AdminLoginParam {
    /**
     * 管理员登录名
     */
    private String name;
    /**
     * 管理员登录密码
     */
    private String pwd;


}
