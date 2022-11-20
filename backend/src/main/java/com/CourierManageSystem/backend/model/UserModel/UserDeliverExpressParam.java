package com.CourierManageSystem.backend.model.UserModel;

import lombok.Data;

@Data
public class UserDeliverExpressParam {
    private String phone;
    private long delivery_address;
    private long recipient_address;
    private Integer price;
}
