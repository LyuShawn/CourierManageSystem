package com.CourierManageSystem.backend.entity;

import lombok.Data;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;

/**
 * @Title user_express 
 * @Description  
 * @author ClementChow 
 * @Date 2022-10-30 
 */

@Data
@TableName ( "user_express" )
public class UserExpress  implements Serializable {

	private static final long serialVersionUID =  4645724453989089139L;

	/**
	 * 用户-快递表id
	 */
   
	@TableId(value = "id")
	private Long id;

	/**
	 * 用户id
	 */
	private Long user;

	/**
	 * 快递id
	 */
	private Long express;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 是否删除
	 */
	private Integer isDelete;

}
