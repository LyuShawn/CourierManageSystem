package com.CourierManageSystem.backend.entity;

import lombok.Data;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;

/**
 * @Title outlets_courier 
 * @Description  
 * @author ClementChow 
 * @Date 2022-10-30 
 */

@Data
@TableName ( "outlets_courier" )
public class OutletsCourier  implements Serializable {

	private static final long serialVersionUID =  3960597067410593271L;

	/**
	 * 网点-快递员表id
	 */
   
	@TableId(value = "id")
	private Long id;

	/**
	 * 网点id
	 */
	private Long outlets;

	/**
	 * 快递员id
	 */
	private Long courier;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 是否删除
	 */
	private Integer isDelete;

}
