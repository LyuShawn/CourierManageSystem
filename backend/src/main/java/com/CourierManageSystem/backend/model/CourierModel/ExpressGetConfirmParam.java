package com.CourierManageSystem.backend.model.CourierModel;

import lombok.Data;

@Data
public class ExpressGetConfirmParam {
    /**
     * 快递员id
     */
    private Long id;
    /**
     * 快递单号
     */
    private String tracking_number;
}
