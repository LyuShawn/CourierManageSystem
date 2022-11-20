package com.CourierManageSystem.backend.model.CourierModel;

import lombok.Data;

@Data
public class CourierInformationChangeParam {

    /**
     * open_id
     */
    private String open_id;

    /**
     * 姓名
     */
    private String full_name;

    /**
     * 手机
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 微信昵称
     */
    private String nickname;

    /**
     * 头像url
     */
    private String avatar_url;

    /**
     * 性别
     */
    private String gender;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 语言
     */
    private String language;

    /**
     * 城市
     */
    private String city;
}
