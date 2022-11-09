package com.CourierManageSystem.backend.model.CourierModel;

import lombok.Data;


@Data
public class CourierCheckExpressResult {
    /**
     * 快递单号
     */
    private String tracking_number;

    /**
     * 收件人地址id
     */
    private Long delivery_address;

    /**
     * 发件人地址id
     */
    private Long recipient_address;
    /**
     * 快递价格
     */
    private Integer price;
    /**
     * 初始网点
     */
    private Long begin_outlets;
    /**
     * 最终网点
     */
    private Long end_outlets;
}
