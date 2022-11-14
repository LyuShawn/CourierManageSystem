package com.CourierManageSystem.backend.controller;

import com.CourierManageSystem.backend.entity.User;
import com.CourierManageSystem.backend.model.UserModel.*;
import com.CourierManageSystem.backend.service.UserService;
import com.CourierManageSystem.backend.util.ResponseWrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseWrapper register(@RequestBody UserRegisterParam userRegisterParam){
        return userService.register(userRegisterParam);
    }

    @PostMapping("/update")
    public ResponseWrapper update(@RequestBody User user)
    {
        return userService.update(user);
    }

    @PostMapping("/user_information")
    public ResponseWrapper user_information(@RequestBody String open_id)
    {
        return userService.user_information(open_id);
    }

    @PostMapping("/add_address")
    public ResponseWrapper add_address(@RequestBody UserAddAddressParam userAddAddressParam)
    {
        return userService.add_address(userAddAddressParam);
    }

    @PostMapping("/change_address")
    public ResponseWrapper change_address(@RequestBody UserChangeAddressParam userChangeAddressParam)
    {
        return userService.change_address(userChangeAddressParam);
    }
    @PostMapping("/delete_address")
    public ResponseWrapper delete_address(@RequestBody String address_id) {
        return userService.delete_address(address_id);
    }

    @GetMapping("/get_all_address")
    public ResponseWrapper get_all_address(String user_id) {
        return userService.get_all_address(user_id);
    }

    @PostMapping("/express_money")
    public ResponseWrapper express_money(@RequestBody UserExpressMoneyParam userExpressMoneyParam) {
        return userService.express_money(userExpressMoneyParam);
    }

    @PostMapping("/delivery_express")
    public ResponseWrapper delivery_express(@RequestBody UserDeliverExpressParam userDeliverExpressParam) {
        return userService.delivery_express(userDeliverExpressParam);
    }

    @PostMapping("/logistics_information")
    public ResponseWrapper logistics_information(@RequestBody String tracking_number) {
        return userService.logistics_information(tracking_number);
    }

    @GetMapping("/get_all_express")
    public ResponseWrapper get_all_express(String phone) {
        return userService.get_all_express(phone);
    }


    @PostMapping("/set_default_address")
    public ResponseWrapper set_default_address(@RequestBody UserSetDefaultAddressParam userSetDefaultAddressParam) {
        return userService.set_default_address(userSetDefaultAddressParam);
    }

    @PostMapping("/login")
    public ResponseWrapper login(@RequestBody JSONObject jsonObject) throws IOException {
        //获得登录凭证code
        String code=jsonObject.getString("code");
        return userService.userLogin(code);
    }
}
