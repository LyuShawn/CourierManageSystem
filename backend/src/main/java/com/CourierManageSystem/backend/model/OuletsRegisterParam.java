package com.CourierManageSystem.backend.model;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
@Data
public class OuletsRegisterParam {
    /**
     * 网点id
     */

    @TableId(value = "id")
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
    private String principalIdentityId;
    /**
     * 负责人姓名
     */
    private String principalName;
}
