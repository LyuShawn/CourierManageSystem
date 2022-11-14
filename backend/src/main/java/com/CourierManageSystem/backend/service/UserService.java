package com.CourierManageSystem.backend.service;

import com.CourierManageSystem.backend.entity.*;
import com.CourierManageSystem.backend.mapper.*;
import com.CourierManageSystem.backend.model.UserModel.*;
import com.CourierManageSystem.backend.util.*;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.ImmutableMap;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

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
    @Autowired
    WechatService wechatService;

    private static final int INVALID = 40029;
    private static final int OFTEN = 45011;

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
     * @param
     * @return
     */

    public ResponseWrapper user_information(UserInformationParam userInformationParam) {
        System.out.println("用户信息获取，参数为:\n" + userInformationParam.getOpen_id().toString());
        Map<String, Object> map = new HashMap<>();
        map.put("open_id", userInformationParam.getOpen_id());
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
        express.setPresent_outlets("");
        express.setBegin_outlets(addressMatchOutlets.Match(express.getDelivery_address()));
        express.setEnd_outlets(addressMatchOutlets.Match(express.getRecipient_address()));
        express.setNow_outlets((long) -1);
        expressMapper.insert(express);
        Map map=new HashMap();
        map.put("tracking_number",express.getTracking_number());
        UserExpress send_userExpress=new UserExpress();
        send_userExpress.setExpress(express.getId());
        send_userExpress.setIs_delete(0);
        send_userExpress.setUser_phone(userDeliverExpressParam.getPhone());
        send_userExpress.setReceive_send(1);
        userExpressMapper.insert(send_userExpress);
        //寄件人用户快递表
        Address receive_address=addressMapper.selectById(userDeliverExpressParam.getRecipient_address());
        UserExpress receive_userExpress=new UserExpress();
        receive_userExpress.setExpress(express.getId());
        receive_userExpress.setIs_delete(0);
        receive_userExpress.setUser_phone(receive_address.getPhone());
        receive_userExpress.setReceive_send(0);
        //收件人用户快递表
        return ResponseWrapper.markSuccess("快递已生成",map);
    }

    /**
     * 用户通过快递单号查询快递信息接口
     *
     * @param
     * @return
     */
    public ResponseWrapper logistics_information(UserLogisticsInformationParam userLogisticsInformationParam)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("tracking_number", userLogisticsInformationParam.getTracking_number());
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
    public ResponseWrapper delete_address(UserDeleteAddressParam userDeleteAddressParam) {
        System.out.println("用户地址删除参数为\n" + userDeleteAddressParam.getAddress_id().toString());
        Address address = addressMapper.selectById(userDeleteAddressParam.getAddress_id());
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

    // 用户登录
    public ResponseWrapper userLogin(String code) throws IOException {
        //登录凭证校验。通过 wx.login 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程
        JSONObject code2Session = wechatService.getCode2Session(code);
        Integer errorCode =code2Session.getInteger("errcode");

        //errcode为0表示请求成功
        if(errorCode==0){
            //将获得的openid 返回
            return ResponseWrapper.markSuccess("用户登录成功",ImmutableMap.of("openId",code2Session.getString("openid")));
            //StpUtil.login(code2Session.getString("openid"));
            // 获取当前会话的token值,将该token值作为skey传给小程序端作为会话的维护

        }
        else if(errorCode==-1){
            return ResponseWrapper.markError("系统繁忙，此时请开发者稍候再试");
        }
        else if(errorCode==INVALID){
            return ResponseWrapper.markError("code 无效");
        }
        else if(errorCode==OFTEN){
            return ResponseWrapper.markError("频率限制，每个用户每分钟100次");
        }
        return ResponseWrapper.markError("系统未知错误");
    }

    public ResponseWrapper get_express_state(UserGetExpressStateParam userGetExpressStateParam)
    {
        Map map1=new HashMap();
        map1.put("tracking_number",userGetExpressStateParam.getTracking_number());
        List<Express> expresses=expressMapper.selectByMap(map1);
        if(expresses.size()==0)
            return ResponseWrapper.markError("无此快递单号，查询失败！");
        Express express=expresses.get(0);
        Map map2=new HashMap();
        map2.put("express",express.getId());
        map2.put("user_phone",userGetExpressStateParam.getPhone());
        List<UserExpress> userExpressList=userExpressMapper.selectByMap(map2);
        Map statemap=new HashMap();
        UserExpress userExpress = userExpressList.get(0);
        //判断收件人和寄件人是不是一个人 如果是一个人 状态为3
        if(userExpressList.size()==1) {
            statemap.put("receive_send",userExpress.getReceive_send());
        }
        else{
            statemap.put("receive_send",3);
        }
        if(express.getPick_up()==0)
        {
            //快递员还未取件 待揽件
            statemap.put("state",1);
            return ResponseWrapper.markSuccess("查询成功！状态为待揽件",statemap);
        }
        else
        {
            if(express.getDelivered()==0)
            {
                //快递员已取件运输中 但是还未签收
                statemap.put("state",2);
                return ResponseWrapper.markSuccess("查询成功！状态为运输中",statemap);
            }
            else
            {
                statemap.put("state",3);
                return ResponseWrapper.markSuccess("查询成功！状态为已签收",statemap);
            }
        }
    }

    public ResponseWrapper get_express_pass_outlets(String tracking_number)
    {
        Map map=new HashMap();
        map.put("tracking_number",tracking_number);
        List<Express> expressList=expressMapper.selectByMap(map);
        if(expressList.size()==0)
            return ResponseWrapper.markError("快递单号有误 查询失败");
        Express express=expressList.get(0);
        String pass_outlets=express.getPresent_outlets();
        List<String> pass_outlets_list=Arrays.asList(pass_outlets.split("-"));
        if(pass_outlets_list.size()<2)
            return ResponseWrapper.markError("查询失败，快递还未运输！");
        List<String> msg=new ArrayList<String>();
        for (int i=1;i<pass_outlets_list.size();i++)
        {
            Outlets outlets=outletsMapper.selectById(pass_outlets_list.get(i));
            msg.add(outlets.getNickname());
        }
        Map map_msg=new HashMap();
        map_msg.put("pass_outlets_list",msg);
        return  ResponseWrapper.markSuccess("查询成功",map_msg);
    }

    public ResponseWrapper get_express_nowoutlets(String tracking_number)
    {
        Map map=new HashMap();
        map.put("tracking_number",tracking_number);
        List<Express> expressList=expressMapper.selectByMap(map);
        if(expressList.size()==0)
            return ResponseWrapper.markError("快递单号有误 查询失败");
        Express express=expressList.get(0);
        Long now_outlets=express.getNow_outlets();
        if(now_outlets==-1)
            return ResponseWrapper.markError("快递待揽件，还未入库");
        Outlets outlets=outletsMapper.selectById(now_outlets);
        Map map_msg=new HashMap();
        map_msg.put("now_outlet",outlets.getNickname());
        return  ResponseWrapper.markSuccess("查询成功",map_msg);
    }

}
