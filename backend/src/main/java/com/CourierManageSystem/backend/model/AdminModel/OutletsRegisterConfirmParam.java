package com.CourierManageSystem.backend.model.AdminModel;

import lombok.Data;

@Data
public class OutletsRegisterConfirmParam {
    /**
     * 网点id
     */
    private Long id;
    /**
     * 审核结果 -1：拒绝，1：同意
     */
    private Integer confirmed;
}
