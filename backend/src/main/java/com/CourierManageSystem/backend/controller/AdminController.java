package com.CourierManageSystem.backend.controller;

import com.CourierManageSystem.backend.model.AdminModel.*;
import com.CourierManageSystem.backend.service.AdminService;
import com.CourierManageSystem.backend.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping ("/login")
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

    /**
     * 网点注册审核
     * @param outletsRegisterConfirmParam
     * @return
     */
    @PostMapping("/outlets_register_confirm")
    public ResponseWrapper outletsRegisterConfirm(@RequestBody OutletsRegisterConfirmParam outletsRegisterConfirmParam){
        return adminService.outletsRegisterConfirm(outletsRegisterConfirmParam);
    }

    /**
     * 删除网点
     * @param deleteOutletsParam
     * @return
     */
    @PostMapping("/delete_outlets")
    public ResponseWrapper deleteOutlets(@RequestBody DeleteOutletsParam deleteOutletsParam){
        return adminService.deleteOutlets(deleteOutletsParam);
    }

    /**
     * 管理员修改密码
     * @param adminUpdatePwdParam
     * @return
     */
    @PostMapping("/update_pwd")
    public ResponseWrapper adminUpdatePwd(@RequestBody AdminUpdatePwdParam adminUpdatePwdParam){
        return adminService.adminUpdatePwd(adminUpdatePwdParam);
    }
}
