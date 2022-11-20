package com.CourierManageSystem.backend.model.OutletsParamAndResult;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
@Data
public class OutletsRegisterParam {
    /**
     * 账号
     */

    private String username;

    /**
     * 密码
     */

    private String pwd;


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
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;
    /**
     * 负责人身份证号
     */
    private String principal_identity_id;
    /**
     * 负责人姓名
     */
    private String principal_name;
}
