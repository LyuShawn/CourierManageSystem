package com.CourierManageSystem.backend.model.AdminModel;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class OutletsRegisterRequestResult {
    /**
     * 网点id
     */

    private Long id;


    /**
     * 网点名称
     */
    private String nickname;


    /**
     * 手机号
     */
    private String phone;

    /**
     * 网点地址
     */
    private String address;


    /**
     * 是否删除
     */
    private Integer isDelete;
    /**
     * 负责人身份证号
     */
    private String principalIdentityId;
    /**
     * 负责人姓名
     */
    private String principalName;
    /**
     * 管理员是否确认注册
     */
    private Integer confirmed;
}
