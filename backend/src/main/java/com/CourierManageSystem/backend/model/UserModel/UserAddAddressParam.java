package com.CourierManageSystem.backend.model.UserModel;

import lombok.Data;

@Data
public class UserAddAddressParam {
    /**
     * 用户id
     */
    private long user;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 地址
     */
    private String addr;
    /**
     * 姓名
     */
    private String name;
}
