package com.CourierManageSystem.backend.model.UserModel;

import com.CourierManageSystem.backend.entity.Express;
import lombok.Data;

@Data
public class UserGetAllExpressResult {
    private Express express;
    private int receive_send;
    private int state;
}
