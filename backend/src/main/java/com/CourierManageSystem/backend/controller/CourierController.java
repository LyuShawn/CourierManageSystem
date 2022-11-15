package com.CourierManageSystem.backend.controller;

import com.CourierManageSystem.backend.model.CourierModel.*;
import com.CourierManageSystem.backend.service.CourierService;
import com.CourierManageSystem.backend.util.ResponseWrapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/courier")
@CrossOrigin
public class CourierController {
    @Autowired
    CourierService courierService;

    /**
     * 快递员注册
     * @param courierRegisterParam
     * @return
     */
    @PostMapping("/register")
    public ResponseWrapper register(@RequestBody CourierRegisterParam courierRegisterParam){
        return courierService.courierRegister(courierRegisterParam);
    }

    /**
     * 快递员信息获取
     * @param courierInformationParam
     * @return
     */
    @GetMapping("/courier_information")
    public ResponseWrapper courierInformation(CourierInformationParam courierInformationParam){
        return courierService.courierInformation(courierInformationParam);
    }

    /**
     * 快递员物流查询
     * @param logisticsInformationParam
     * @return
     */
    @GetMapping("/logistics_information")
    public ResponseWrapper logisticsInformation(LogisticsInformationParam logisticsInformationParam){
        return courierService.logisticsInformation(logisticsInformationParam);
    }

    /**
     * 快递员送件确认
     * @param expressReachConfirmParam
     * @return
     */
    @PostMapping("/express_reach_confirm")
    public ResponseWrapper expressReachConfirm(@RequestBody ExpressReachConfirmParam expressReachConfirmParam){
        return courierService.expressReachConfirm(expressReachConfirmParam);
    }

    /**
     * 快递员信息修改
     * @param courierInformationChangeParam
     * @return
     */
    @PostMapping("/information_change")
    public ResponseWrapper courierInformationChange(@RequestBody CourierInformationChangeParam courierInformationChangeParam){
        return courierService.courierInformationChange(courierInformationChangeParam);
    }

    /**
     * 快递员查看网点任务
     * @param courierCheckOutletsTaskParam
     * @return
     */
    @GetMapping("/check_outlets_task")
    public ResponseWrapper courierCheckOutletsTask(CourierCheckOutletsTaskParam courierCheckOutletsTaskParam){
        return courierService.courierCheckOutletsTask(courierCheckOutletsTaskParam);
    }

    /**
     * 快递员接单
     * @param courierAcceptTaskParam
     * @return
     */
    @PostMapping("/accept_task")
    public ResponseWrapper courierAcceptTask(@RequestBody CourierAcceptTaskParam courierAcceptTaskParam){
        return courierService.courierAcceptTask(courierAcceptTaskParam);
    }

    /**
     * 快递员查看负责过的快递
     * @param courierCheckExpressParam
     * @return
     */
    @GetMapping("/check_express")
    public ResponseWrapper courierCheckExpress(CourierCheckExpressParam courierCheckExpressParam){
        return courierService.courierCheckExpress(courierCheckExpressParam);
    }

    /**
     * 快递员确认取件
     * @param expressGetConfirmParam
     * @return
     */
    @PostMapping("/express_get_confirm")
    public ResponseWrapper expressGetConfirm(@RequestBody ExpressGetConfirmParam expressGetConfirmParam){
        return courierService.expressGetConfirm(expressGetConfirmParam);
    }

    /**
     * 快递员申请网点
     * @param courierApplyOutletsParam
     * @return
     */
    @PostMapping("/apply_outlets")
    public ResponseWrapper courierApplyOutlets(@RequestBody CourierApplyOutletsParam courierApplyOutletsParam){
        return courierService.courierApplyOutlets(courierApplyOutletsParam);
    }

    @PostMapping("/login")
    public ResponseWrapper login(@RequestBody JSONObject jsonObject) throws IOException {
        //获得登录凭证code
        String code=jsonObject.getString("code");
        return courierService.userLogin(code);
    }

}
