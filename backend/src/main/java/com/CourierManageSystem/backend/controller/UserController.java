package com.CourierManageSystem.backend.controller;

import com.CourierManageSystem.backend.entity.User;
import com.CourierManageSystem.backend.model.UserModel.*;
import com.CourierManageSystem.backend.service.UserService;
import com.CourierManageSystem.backend.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseWrapper register(UserRegisterParam userRegisterParam){
        return userService.register(userRegisterParam);
    }

    @PostMapping("/update")
    public ResponseWrapper update(User user)
    {
        return userService.update(user);
    }

    @PostMapping("/user_information")
    public ResponseWrapper user_information(String open_id)
    {
        return userService.user_information(open_id);
    }

    @PostMapping("/add_address")
    public ResponseWrapper add_address(UserAddAddressParam userAddAddressParam)
    {
        return userService.add_address(userAddAddressParam);
    }

    @PostMapping("/change_address")
    public ResponseWrapper change_address(UserChangeAddressParam userChangeAddressParam)
    {
        return userService.change_address(userChangeAddressParam);
    }
    @PostMapping("/delete_address")
    public ResponseWrapper delete_address(String address_id) {
        return userService.delete_address(address_id);
    }

    @GetMapping("/get_all_address")
    public ResponseWrapper get_all_address(String user_id) {
        return userService.get_all_address(user_id);
    }

    @PostMapping("/express_money")
    public ResponseWrapper express_money(UserExpressMoneyParam userExpressMoneyParam) {
        return userService.express_money(userExpressMoneyParam);
    }

    @PostMapping("/delivery_express")
    public ResponseWrapper delivery_express(UserDeliverExpressParam userDeliverExpressParam) {
        return userService.delivery_express(userDeliverExpressParam);
    }

    @PostMapping("/logistics_information")
    public ResponseWrapper logistics_information(String tracking_number) {
        return userService.logistics_information(tracking_number);
    }

    @GetMapping("/get_all_express")
    public ResponseWrapper get_all_express(String phone) {
        return userService.get_all_express(phone);
    }


    @PostMapping("/set_default_address")
    public ResponseWrapper set_default_address(UserSetDefaultAddressParam userSetDefaultAddressParam) {
        return userService.set_default_address(userSetDefaultAddressParam);
    }
}
