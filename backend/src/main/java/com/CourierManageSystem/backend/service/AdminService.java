package com.CourierManageSystem.backend.service;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.CourierManageSystem.backend.entity.Admin;
import com.CourierManageSystem.backend.entity.Outlets;
import com.CourierManageSystem.backend.mapper.AdminMapper;
import com.CourierManageSystem.backend.mapper.OutletsMapper;
import com.CourierManageSystem.backend.model.AdminModel.*;
import com.CourierManageSystem.backend.util.ResponseWrapper;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    OutletsMapper outletsMapper;

    /**
     * 管理员登录
     * @param adminLoginParam
     * @return
     */
    public ResponseWrapper login(AdminLoginParam adminLoginParam){

        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getName,adminLoginParam.getName());
        Admin admin = adminMapper.selectOne(queryWrapper);
        if (admin != null){
            String salt=admin.getSalt();
            AdminLoginResult adminLoginResult = new AdminLoginResult();
            adminLoginResult.setId(admin.getId().intValue());
            String pwd=(salt==null||salt.length()==0)?adminLoginParam.getPwd():SaSecureUtil.md5BySalt(adminLoginParam.getPwd(),admin.getSalt());
            if(adminMapper.selectByMap(ImmutableMap.of("pwd",pwd)).isEmpty()){
                    return ResponseWrapper.markError("用户名或密码不正确");
            }
            return ResponseWrapper.markSuccess("登录成功",adminLoginResult);
        }
        else{
            return ResponseWrapper.markError("用户名或密码不正确");
        }
    }

    /**
     * 添加管理员
     * @param adminAddParam
     * @return
     */
    public ResponseWrapper add(AdminAddParam adminAddParam){
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getName,adminAddParam.getName());
        if(adminMapper.selectCount(queryWrapper)>0){
            return ResponseWrapper.markError("姓名已存在");
        }

        Admin admin = new Admin(adminAddParam.getName(),adminAddParam.getPwd());

        adminMapper.insert(admin);
        return ResponseWrapper.markSuccess("新增成功");
    }

    /**
     * 获取网点列表
     * @return
     */
    public ResponseWrapper showOutlets(){
        LambdaQueryWrapper<Outlets> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Outlets::getIs_delete,0);
        List<Outlets> outlets = outletsMapper.selectList(queryWrapper);
        //List<ShowOutletsResult> showOutletsResults = JSON.parseArray(JSON.toJSONString(outlets)).toJavaList(ShowOutletsResult.class);
        return ResponseWrapper.markSuccess("获取成功",outlets);
    }

    /**
     * 获取网点注册请求
     * @return
     */
    public ResponseWrapper outletsRegisterRequest(){
        LambdaQueryWrapper<Outlets> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Outlets::getConfirmed,0);
        List<Outlets> outlets = outletsMapper.selectList(queryWrapper);
        List<OutletsRegisterRequestResult> outletsRegisterRequestResults = JSON.parseArray(JSON.toJSONString(outlets)).toJavaList(OutletsRegisterRequestResult.class);
        return ResponseWrapper.markSuccess("查询成功",outletsRegisterRequestResults);
    }

    /**
     *  网点注册审核
     * @param outletsRegisterConfirmParam
     * @return
     */
    public ResponseWrapper outletsRegisterConfirm(OutletsRegisterConfirmParam outletsRegisterConfirmParam){
        LambdaQueryWrapper<Outlets> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Outlets::getId,outletsRegisterConfirmParam.getId());
        Outlets selectOutlets = outletsMapper.selectOne(queryWrapper);
        if(selectOutlets != null) {
            Outlets outlets = new Outlets();
            outlets.setId(outletsRegisterConfirmParam.getId());
            outlets.setConfirmed(outletsRegisterConfirmParam.getConfirmed());
            outletsMapper.updateById(outlets);
            return ResponseWrapper.markSuccess("成功确认");
        }
        else{
            return ResponseWrapper.markError("该id:"+outletsRegisterConfirmParam.getId()+"对应网点不存在");
        }
    }

    /**
     * 删除网点
     * @param deleteOutletsParam
     * @return
     */
    public ResponseWrapper deleteOutlets(DeleteOutletsParam deleteOutletsParam){
        LambdaQueryWrapper<Outlets> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Outlets::getId,deleteOutletsParam.getId());

        if (outletsMapper.selectOne(queryWrapper) == null) {
            return ResponseWrapper.markError("该id对应的网点不存在");
        }
        outletsMapper.delete(queryWrapper);
        return ResponseWrapper.markSuccess("删除成功");
    }

    /**
     * 管理员修改密码
     * @param adminUpdatePwdParam
     * @return
     */
    public ResponseWrapper adminUpdatePwd(AdminUpdatePwdParam adminUpdatePwdParam){
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getName,adminUpdatePwdParam.getName());

        Admin admin=adminMapper.selectOne(queryWrapper);
        queryWrapper.eq(Admin::getPwd,SaSecureUtil.md5BySalt(adminUpdatePwdParam.getOldPwd(),admin.getSalt()));
        if(adminMapper.selectOne(queryWrapper) == null){
            return ResponseWrapper.markError("旧密码不匹配");
        }
        LambdaUpdateWrapper<Admin> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Admin::getName,adminUpdatePwdParam.getName());
        updateWrapper.set(Admin::getPwd,SaSecureUtil.md5BySalt(adminUpdatePwdParam.getNewPwd(),admin.getSalt()));
        adminMapper.update(null,updateWrapper);
        return ResponseWrapper.markSuccess("修改成功");
    }

}
