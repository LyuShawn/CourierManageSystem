package com.CourierManageSystem.backend.entity;

import lombok.Data;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;

/**
 * @Title courier 
 * @Description  
 * @author ClementChow 
 * @Date 2022-10-30 
 */

@Data
@TableName ( "courier" )
public class Courier  implements Serializable {

	private static final long serialVersionUID =  8180686154487708189L;

	/**
	 * id
	 */
   
	@TableId(value = "id")
	private Long id;

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

	/**
	 * 更新时间
	 */
	private Date update_time;
	/**
	 * 逻辑删除
	 */
	private Integer is_delete;

}
