package com.CourierManageSystem.backend.model.ExpressModel;

import com.CourierManageSystem.backend.entity.Address;
import com.CourierManageSystem.backend.entity.Express;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExpressResult {
    private Express express;
    private Address deliveryAddress;
    private Address recipientAddress;

}
