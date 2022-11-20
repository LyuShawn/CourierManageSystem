package com.CourierManageSystem.backend;

import com.CourierManageSystem.backend.util.ResponseWrapper;
import com.CourierManageSystem.backend.mapper.AdminMapper;
import com.CourierManageSystem.backend.entity.Admin;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BackendApplicationTests {

@Autowired
    AdminMapper adminMapper;

    @Test
    void contextLoads() {
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getId,2);
        Admin admin = adminMapper.selectById(2);
        System.out.println(admin);
        List<Admin> admins = adminMapper.selectList(null);
        System.out.println(admins);
        System.out.println(ResponseWrapper.markSuccess("获取成功",admins));
    }

}
