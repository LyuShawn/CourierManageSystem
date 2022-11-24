package com.CourierManageSystem.backend.entity;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.hutool.core.util.RandomUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import lombok.NoArgsConstructor;

/**
 * @Title admin 
 * @Description  
 * @author ClementChow 
 * @Date 2022-10-30 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName ( "admin" )
public class Admin  implements Serializable {

	public Admin(String name,String originPwd){
		this.name=name;
		//随机8位盐
		this.salt= RandomUtil.randomString (8);
		this.pwd= SaSecureUtil.md5BySalt(originPwd,this.salt);
	}


	/**
	 * 管理员表id
	 */
   
	@TableId(value = "id",type = IdType.AUTO)
	private Long id;

	/**
	 * 管理员登录名
	 */
	private String name;

	/**
	 * 管理员登录密码
	 */
	private String pwd;

	private String salt;
}
