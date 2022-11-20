package com.CourierManageSystem.backend.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName( "address" )
public class Address {
    @TableId(value = "id",type = IdType.AUTO)
    /**
     * 地址表id
     */
    private long id;
    /**
     * user_id
     */
    private long user;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 地址
     */
    private String addr;
    /**
     * 是否删除
     */
    private Integer is_delete;

    /**
     * 地址经纬度
     */
    private String location;

    private String name;
}
