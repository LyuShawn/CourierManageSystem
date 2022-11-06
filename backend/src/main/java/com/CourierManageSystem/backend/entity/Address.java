package com.CourierManageSystem.backend.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName( "address" )
public class Address {
    @TableId(value = "id")
    /**
     * 地址表id
     */
    private long id;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 地址
     */
    private String addr;
    /**
     * 网点id
     */
    private Long outlets;
    /**
     * 用户id
     */
    private Long user;
    /**
     * 是否删除
     */
    private Integer isDelete;
}
