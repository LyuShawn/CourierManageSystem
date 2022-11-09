package com.CourierManageSystem.backend.model.CourierModel;

import lombok.Data;

@Data
public class CourierInformationResult {
    private Long id;

    /**
     * open_id
     */
    private String openId;

    /**
     * 姓名
     */
    private String fullName;

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
    private String avatarUrl;

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
