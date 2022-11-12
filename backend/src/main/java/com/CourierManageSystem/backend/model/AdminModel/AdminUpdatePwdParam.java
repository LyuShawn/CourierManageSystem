package com.CourierManageSystem.backend.model.AdminModel;

import lombok.Data;

@Data
public class AdminUpdatePwdParam {
    /**
     * 管理员id
     */
    private Long id;
    /**
     * 密码
     */
    private String pwd;
}
