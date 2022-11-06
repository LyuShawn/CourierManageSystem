package com.CourierManageSystem.backend.entity;

import lombok.Data;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;

/**
 * @Title admin 
 * @Description  
 * @author ClementChow 
 * @Date 2022-10-30 
 */

@Data
@TableName ( "admin" )
public class Admin  implements Serializable {

	private static final long serialVersionUID =  904390091839455403L;

	/**
	 * 管理员表id
	 */
   
	@TableId(value = "id")
	private Long id;

	/**
	 * 管理员登录名
	 */
	private String name;

	/**
	 * 管理员登录密码
	 */
	private String pwd;

}
