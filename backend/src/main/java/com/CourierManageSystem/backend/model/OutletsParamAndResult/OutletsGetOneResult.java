package com.CourierManageSystem.backend.model.OutletsParamAndResult;

import lombok.Data;

@Data
public class OutletsGetOneResult {
    private long id;
    private String username;
    private String nickname;
    private String phone;
    private String address;
    private String principal_identity_id;
    private String principal_name;
    private Integer confirmed;
    private String location;
}
