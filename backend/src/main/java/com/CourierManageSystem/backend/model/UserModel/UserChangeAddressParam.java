package com.CourierManageSystem.backend.model.UserModel;

import lombok.Data;

@Data
public class UserChangeAddressParam {
    /**
     * 地址id
     */
    private long address_id;
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
