package com.CourierManageSystem.backend.entity;

import lombok.Data;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;
import java.io.InputStream;

/**
 * @Title outlets 
 * @Description  
 * @author ClementChow 
 * @Date 2022-10-30 
 */

@Data
@TableName ( "outlets" )
public class Outlets  implements Serializable {

	private static final long serialVersionUID =  3561775177877682016L;

	/**
	 * 网点id
	 */
   
	@TableId(value = "id")
	private Long id;


	/**
	 * 网点名称
	 */
	private String nickname;


	/**
	 * 手机号
	 */
	private String phone;

	/**
	 * 网点地址
	 */
	private String address;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	/**
	 * 是否删除
	 */
	private Integer isDelete;
	/**
	 * 负责人身份证号
	 */
	private String principalIdentityId;
	/**
	 * 负责人姓名
	 */
	private String principalName;
	/**
	 * 管理员是否确认注册
	 */
	private Integer confirmed;

}
