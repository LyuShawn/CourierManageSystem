package com.CourierManageSystem.backend.entity;

import lombok.Data;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.*;
import java.util.Date;
import java.io.InputStream;

/**
 * @Title user 
 * @Description  
 * @author ClementChow 
 * @Date 2022-10-30 
 */

@Data
@TableName ( "user" )
public class User  implements Serializable {

	private static final long serialVersionUID =  9143998439313778686L;

   
	@TableId(value = "id")
	private Long id;

	private String open_id;

	private String nickname;

	private String avatar_url;

	private Integer gender;

	private String country;

	private String province;

	private String language;

	private String city;

	private Date update_time;

	private Long default_address;

	private String phone;
}
