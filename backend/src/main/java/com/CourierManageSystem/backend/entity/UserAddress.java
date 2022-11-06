package com.CourierManageSystem.backend.entity;

import lombok.Data;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;

/**
 * @Title user_address 
 * @Description  
 * @author ClementChow 
 * @Date 2022-10-30 
 */

@Data
@TableName ( "user_address" )
public class UserAddress  implements Serializable {

	private static final long serialVersionUID =  6651509789474000196L;

	/**
	 * 用户-地址表id
	 */
   
	@TableId(value = "id")
	private Long id;

	/**
	 * 用户id
	 */
	private Long user;

	/**
	 * 地址id
	 */
	private Long address;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 是否删除
	 */
	private Integer isDelete;

}
