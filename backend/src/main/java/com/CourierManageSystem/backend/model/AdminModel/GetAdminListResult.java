package com.CourierManageSystem.backend.model.AdminModel;

import lombok.Data;

@Data
public class GetAdminListResult {
    private Long id;

    /**
     * 管理员登录名
     */
    private String name;

    /**
     * 管理员登录密码
     */
    private String pwd;
}
