package com.CourierManageSystem.backend.model.CourierModel;

import lombok.Data;

@Data
public class LogisticsInformationResult {

    /**
     * 取件快递员id
     */
    private Long pickCourier;

    /**
     * 收件人地址id
     */
    private Long deliveryAddress;

    /**
     * 发件人地址id
     */
    private Long recipientAddress;

    /**
     * 中途经过的所有网点id
     */
    private String presentOutlets;
    /**
     * 是否妥投
     */
    private Integer delivered;
    /**
     * 是否取件
     */
    private Integer pickUp;
    /**
     * 快递价格
     */
    private Integer price;
    /**
     * 是否支付
     */
    private Integer isPayment;
    /**
     * 送件快递员id
     */
    private Long deliveryCourier;
    /**
     * 初始网点
     */
    private Long beginOutlets;
    /**
     * 最终网点
     */
    private Long endOutlets;
}
