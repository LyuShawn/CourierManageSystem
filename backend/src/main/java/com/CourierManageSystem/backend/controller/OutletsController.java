package com.CourierManageSystem.backend.controller;


import com.CourierManageSystem.backend.entity.Outlets;
import com.CourierManageSystem.backend.model.OutletsParamAndResult.*;
import com.CourierManageSystem.backend.service.OutletsService;
import com.CourierManageSystem.backend.util.ResponseWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/outlets")
@CrossOrigin
public class OutletsController {
    @Autowired
    private OutletsService outletsService;

    /**
     * 网点注册
     * @param outletsRegisterParam
     * @return
     */
    @PostMapping("/register")
    public ResponseWrapper register(@RequestBody OutletsRegisterParam outletsRegisterParam){
        return outletsService.register(outletsRegisterParam);
    }

    /**
     * 获取单个网点
     * @return
     */
    @PostMapping("/getOne")
    public ResponseWrapper getOne(@RequestBody OutletsGetOneParam outletsGetOneParam){
        return outletsService.getOne(outletsGetOneParam);
    }

    /**
     * 查看快递员申请表
     * @return
     */
    @PostMapping("/showCourierApplyList")
    public ResponseWrapper showCourierApplyList(@RequestBody OutletsCourierApplyParam outletsCourierApplyParam){
        return outletsService.showCourierApplyList(outletsCourierApplyParam);
    }

    /**
     * 同意快递员申请
     * @return
     */
    @PostMapping("/courierIsJoin")
    public ResponseWrapper courierIsJoin(@RequestBody OutletsCourierIsJoinParam outletsCourierIsJoinParam){
        return outletsService.courierIsJoin(outletsCourierIsJoinParam);
    }

    /**
     * 查看所有快递
     * @return
     */
    @PostMapping("/showAllExpress")
    public ResponseWrapper showAllExpress(@RequestBody OutletsExpressAllParam outletsExpressAllParam){
        return outletsService.showAllExpress(outletsExpressAllParam);
    }

    /**
     * 快递入库
     * @return
     */
    @PostMapping("/getExpress")
    public ResponseWrapper getExpress(@RequestBody OutletsGetExpressParam outletsGetExpressParam){
        return outletsService.getExpress(outletsGetExpressParam);
    }
}
