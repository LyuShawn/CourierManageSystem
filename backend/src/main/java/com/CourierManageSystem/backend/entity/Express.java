package com.CourierManageSystem.backend.entity;

import lombok.Data;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;

/**
 * @Title express 
 * @Description  
 * @author ClementChow 
 * @Date 2022-10-30 
 */

@Data
@TableName ( "express" )
public class Express  implements Serializable {

	private static final long serialVersionUID =  5214522032538294961L;

	/**
	 * 地址表id
	 */
   
	@TableId(value = "id",type = IdType.AUTO)
	private Long id;


	/**
	 * 快递单号
	 */
	private Long tracking_number;

	/**
	 * 取件快递员id
	 */
	private Long pick_courier;

	/**
	 * 收件人地址id
	 */
	private Long delivery_address;

	/**
	 * 发件人地址id
	 */
	private Long recipient_address;

	/**
	 * 中途经过的所有网点id
	 */
	private String present_outlets;
	/**
	 * 是否妥投
	 */
	private Integer delivered;
	/**
	 * 更新实践
	 */
	private Date update_time;
	/**
	 * 是否删除
	 */
	private Integer is_delete;
	/**
	 * 是否取件
	 */
	private Integer pick_up;
	/**
	 * 快递价格
	 */
	private Integer price;
	/**
	 * 是否支付
	 */
	private Integer is_payment;
	/**
	 * 送件快递员id
	 */
	private Long delivery_courier;
	/**
	 * 初始网点
	 */
	private Long begin_outlets;
	/**
	 * 最终网点
	 */
	private Long end_outlets;
	/**
	 * 当前网点
	 */
	private Long now_outlets;


}
