package com.CourierManageSystem.backend.model.UserModel;

import lombok.Data;

@Data
public class UserRegisterParam {

    /**
     * 微信openid
     */
    private String open_id;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像url
     */
    private String avatar_url;

    /**
     * 性别
     */
    private Integer gender;

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

    /**
     * 手机号
     */
    private String phone;
}
