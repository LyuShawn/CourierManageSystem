package com.CourierManageSystem.backend.model.AdminModel;

import lombok.Data;

@Data
public class AdminUpdatePwdParam {
    /**
     * 管理员id
     */
    private Long id;
    /**
     * 旧密码
     */
    private String oldPwd;
    /**
     * 新密码
     */
    private String newPwd;
}
