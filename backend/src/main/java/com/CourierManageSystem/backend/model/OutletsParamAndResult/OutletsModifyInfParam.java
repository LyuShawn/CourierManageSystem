package com.CourierManageSystem.backend.model.OutletsParamAndResult;

import lombok.Data;

@Data
public class OutletsModifyInfParam {
    private long id;
    private String username;
    private String pwd;
    private String nickname;
    private String address;
    private String phone;
    private String principal_name;
    private String principal_identity_id;
}
