package com.CourierManageSystem.backend.model.CourierModel;

import lombok.Data;

@Data
public class CourierApplyOutletsParam {
    /**
     * 快递员id
     */
    private Long courier_id;
    /**
     * 网点id
     */
    private Long outlets_id;

}
