package com.CourierManageSystem.backend.model;

import lombok.Data;

@Data
public class ShowOutletsResult {
    /**
     * 网点id
     */
    private Integer id;
    /**
     * 网点名称
     */
    private String nickname;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 网点地址
     */
    private String address;
    /**
     * 负责人身份证号
     */
    private String principalIdentityId;
    /**
     * 负责人姓名
     */
    private String principalName;
}
