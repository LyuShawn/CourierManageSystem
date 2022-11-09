package com.CourierManageSystem.backend.service;

import com.CourierManageSystem.backend.entity.*;
import com.CourierManageSystem.backend.mapper.*;
import com.CourierManageSystem.backend.model.UserModel.*;
import com.CourierManageSystem.backend.util.*;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    OutletsMapper outletsMapper;
    @Autowired
    CourierMapper courierMapper;
    @Autowired
    AddressMapper addressMapper;;
    @Autowired
    ExpressMapper expressMapper;
    @Autowired
    AddressMatchOutlets addressMatchOutlets;
    @Autowired
    UserExpressMapper userExpressMapper;
    /**
     * 用户注册接口
     *
     * @param userRegisterParam
     * @return
     */
    public ResponseWrapper register(UserRegisterParam userRegisterParam) {
        System.out.println("用户注册参数为" + userRegisterParam.toString());
        Map<String, Object> map = new HashMap<>();
        map.put("open_id", userRegisterParam.getOpen_id());
        List<User> users = userMapper.selectByMap(map);
        if (users.size() != 0)
            return ResponseWrapper.markSuccess("已注册");
        User user = new User();
        user.setOpen_id(userRegisterParam.getOpen_id());
        user.setAvatar_url(userRegisterParam.getAvatar_url());
        user.setCity(userRegisterParam.getCity());
        user.setCountry(userRegisterParam.getCountry());
        user.setLanguage(userRegisterParam.getLanguage());
        user.setGender(userRegisterParam.getGender());
        user.setProvince(userRegisterParam.getProvince());
        user.setNickname(userRegisterParam.getNickname());
        user.setPhone(userRegisterParam.getPhone());
        userMapper.insert(user);
        System.out.println("用户注册成功\n" + user.toString());
        return ResponseWrapper.markSuccess("注册成功");
    }

    /**
     * 用户修改信息接口
     *
     * @param user
     * @return
     */

    public ResponseWrapper update(User user) {
        System.out.println("用户信息修改，参数为:\n" + user.toString());
        int result = userMapper.updateById(user);
        if (result == 0) {
            return ResponseWrapper.markError("id有误，更新失败！");
        }
        return ResponseWrapper.markError("更新成功！");
    }

    /**
     * 获取用户信息接口
     *
     * @param open_id
     * @return
     */

    public ResponseWrapper user_information(String open_id) {
        System.out.println("用户信息获取，参数为:\n" + open_id.toString());
        Map<String, Object> map = new HashMap<>();
        map.put("open_id", open_id);
        List<User> users = userMapper.selectByMap(map);
        if (users.size() == 0)
            return ResponseWrapper.markError("open_id不存在");
        return ResponseWrapper.markSuccess("获取用户信息成功!!", users.get(0));
    }

    /**
     * 用户寄件接口
     *
     * @param
     * @return
     */

    public ResponseWrapper delivery_express(UserDeliverExpressParam userDeliverExpressParam) {
        Express express=new Express();
        express.setDelivery_address(userDeliverExpressParam.getDelivery_address());
        express.setRecipient_address(userDeliverExpressParam.getRecipient_address());
        express.setPrice(userDeliverExpressParam.getPrice());
        express.setTracking_number(expressMapper.selectOne(Wrappers.<Express>query().orderByDesc("tracking_number").last("limit 1")).getTracking_number()+1);
        express.setDelivered(0);
        express.setIs_delete(0);
        express.setPick_up(0);
        express.setIs_payment(0);
        express.setBegin_outlets(addressMatchOutlets.Match(express.getDelivery_address()));
        express.setEnd_outlets(addressMatchOutlets.Match(express.getRecipient_address()));
        express.setNow_outlets((long) -1);
        expressMapper.insert(express);
        Map map=new HashMap();
        map.put("tracking_number",express.getTracking_number());
        UserExpress userExpress=new UserExpress();
        userExpress.setExpress(express.getId());
        userExpress.setIs_delete(0);
        userExpress.setUser_phone(userDeliverExpressParam.getPhone());
        userExpressMapper.insert(userExpress);
        return ResponseWrapper.markSuccess("快递已生成",map);
    }

    /**
     * 用户通过快递单号查询快递信息接口
     *
     * @param
     * @return
     */
    public ResponseWrapper logistics_information(String tracking_number)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("tracking_number", tracking_number);
        List<Express> expresses = expressMapper.selectByMap(map);
        if(expresses.size()==0)
            return ResponseWrapper.markError("查询失败，无此快递单号");
        return  ResponseWrapper.markSuccess("查询成功!",expresses.get(0));
    }

    /**
     * 用户增加地址接口
     *
     * @param
     * @return
     */
    public ResponseWrapper add_address(UserAddAddressParam userAddAddressParam) {
        System.out.println("用户地址增加参数为\n" + userAddAddressParam.toString());
        Address address = new Address();
        address.setAddr(userAddAddressParam.getAddr());
        address.setPhone(userAddAddressParam.getPhone());
        address.setUser(userAddAddressParam.getUser());
        address.setIs_delete(0);
        address.setLocation(GetGeocoding.getGeocoding(userAddAddressParam.getAddr()));
        addressMapper.insert(address);
        return ResponseWrapper.markSuccess("地址增加成功!");
    }

    /**
     * 用户修改地址信息接口
     *
     * @param
     * @return
     */
    public ResponseWrapper change_address(UserChangeAddressParam userChangeAddressParam) {
        System.out.println("用户地址修改参数为\n" + userChangeAddressParam.toString());
        Address address = addressMapper.selectById(userChangeAddressParam.getAddress_id());
        if (address == null)
            return ResponseWrapper.markError("ID有误，地址更新失败");
        address.setPhone(userChangeAddressParam.getPhone());
        address.setAddr(userChangeAddressParam.getAddr());
        address.setLocation(GetGeocoding.getGeocoding(userChangeAddressParam.getAddr()));
        addressMapper.updateById(address);
        return ResponseWrapper.markSuccess("地址修改成功!");
    }

    /**
     * 用户删除地址信息接口
     *
     * @param
     * @return
     */
    public ResponseWrapper delete_address(String address_id) {
        System.out.println("用户地址删除参数为\n" + address_id.toString());
        Address address = addressMapper.selectById(address_id);
        if (address == null)
            return ResponseWrapper.markError("删除失败，id异常！");
        address.setIs_delete(1);
        addressMapper.updateById(address);
        return ResponseWrapper.markSuccess("删除成功");
    }
    /**
     * 用户获取所有地址信息接口
     *
     * @param
     * @return
     */
    public ResponseWrapper get_all_address(String user_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user_id);
        map.put("is_delete", 0);
        List<Address> address_list= addressMapper.selectByMap(map);
        return ResponseWrapper.markSuccess("查询用户地址成功!",address_list);
    }
    /**
     * 快递金额计算
     *
     * @param
     * @return
     */
    public ResponseWrapper express_money(UserExpressMoneyParam userExpressMoneyParam)
    {
        String delivery_location=GetGeocoding.getGeocoding(userExpressMoneyParam.getDelivery_address());
        String recipient_location=GetGeocoding.getGeocoding(userExpressMoneyParam.getRecipient_address());
        double lat1=Double.parseDouble(delivery_location.split(",")[0]);
        double lng1=Double.parseDouble(delivery_location.split(",")[1]);
        double lat2=Double.parseDouble(recipient_location.split(",")[0]);
        double lng2=Double.parseDouble(recipient_location.split(",")[1]);
        double distance=LocationUtils.getDistance(lat1,lng1,lat2,lng2);
        Map moneymap= new HashMap();
        int money=(int) (distance/100000);
        if(money<12)
            money=12;
        moneymap.put("money",money);
        return ResponseWrapper.markSuccess("价格计算成功！",moneymap);
    }
    /**
     * 用户获得全部地址
     *
     * @param
     * @return
     */
    public ResponseWrapper get_all_express(String phone)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("user_phone", phone);
        List<UserExpress> userExpresses=userExpressMapper.selectByMap(map);
        ArrayList<Express> expresses = new ArrayList();
        for(int i=0;i<userExpresses.size();i++)
        {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("id", userExpresses.get(i).getExpress());
            List<Express> expresses1=expressMapper.selectByMap(map1);
            expresses.add(expresses1.get(0));
        }
        return ResponseWrapper.markSuccess("查找用户快递信息成功！",expresses);
    }
    /**
     * 用户设置默认地址
     *
     * @param
     * @return
     */
    public ResponseWrapper set_default_address(UserSetDefaultAddressParam userSetDefaultAddressParam)
    {
        User user=userMapper.selectById(userSetDefaultAddressParam.getUser_id());
        if(user==null)
            return ResponseWrapper.markError("设置失败，用户id不存在！");
        user.setDefault_address(userSetDefaultAddressParam.getAddress_id());
        int result=userMapper.updateById(user);
        return ResponseWrapper.markSuccess("用户默认地址设置成功!");
    }
}
