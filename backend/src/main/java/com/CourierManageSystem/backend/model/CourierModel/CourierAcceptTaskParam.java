package com.CourierManageSystem.backend.model.CourierModel;

import lombok.Data;

@Data
public class CourierAcceptTaskParam {
    /**
     * 快递员id
     */
    private Long courier_id;
    /**
     * 快递单号
     */
    private String tracking_number;
    /**
     * 0送件/1取件
     */
    private Integer status;
}
