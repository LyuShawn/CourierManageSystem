package com.CourierManageSystem.backend.controller;

import com.CourierManageSystem.backend.model.AdminAddParam;
import com.CourierManageSystem.backend.model.AdminLoginParam;
import com.CourierManageSystem.backend.service.AdminService;
import com.CourierManageSystem.backend.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    /**
     * 管理员登录
     * @param adminLoginParam
     * @return
     */
    @PostMapping("/login")
    public ResponseWrapper login(AdminLoginParam adminLoginParam){
        return adminService.login(adminLoginParam);
    }

    /**
     * 添加管理员
     * @param adminAddParam
     * @return
     */
    @PostMapping("/add")
    public ResponseWrapper add(AdminAddParam adminAddParam){
        return adminService.add(adminAddParam);
    }

    /**
     * 获取网点列表
     * @return
     */
    @GetMapping("/show_outlets")
    public ResponseWrapper showOutlets(){
        return adminService.showOutlets();
    }

    /**
     * 获取网点注册请求
     * @return
     */
    @GetMapping("/outlets_register_request")
    public ResponseWrapper outletsRegisterRequest(){
        return adminService.outletsRegisterRequest();
    }

}
