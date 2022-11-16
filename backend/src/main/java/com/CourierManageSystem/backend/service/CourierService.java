package com.CourierManageSystem.backend.service;


import com.CourierManageSystem.backend.entity.Courier;
import com.CourierManageSystem.backend.entity.Express;
import com.CourierManageSystem.backend.entity.OutletsCourier;
import com.CourierManageSystem.backend.mapper.CourierMapper;
import com.CourierManageSystem.backend.mapper.ExpressMapper;
import com.CourierManageSystem.backend.mapper.OutletsCourierMapper;
import com.CourierManageSystem.backend.model.CourierModel.*;
import com.CourierManageSystem.backend.util.ResponseWrapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class CourierService {
    @Autowired
    CourierMapper courierMapper;
    @Autowired
    ExpressMapper expressMapper;
    @Autowired
    OutletsCourierMapper outletsCourierMapper;

    private static final int INVALID = 40029;
    private static final int OFTEN = 45011;
    @Autowired
    CourierWechatService courierWechatService;

    /**
     * 快递员注册
     * @param courierRegisterParam
     * @return
     */
    public Long courierRegister(CourierRegisterParam courierRegisterParam){
        LambdaQueryWrapper<Courier> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Courier::getOpen_id,courierRegisterParam.getOpen_id());
        if(courierMapper.selectCount(queryWrapper) > 0 ){
            return null;
        }
        Courier courier = new Courier();
        courier.setCountry(courierRegisterParam.getCountry());
        courier.setOpen_id(courierRegisterParam.getOpen_id());
        courier.setCity(courierRegisterParam.getCity());
        courier.setAvatar_url(courierRegisterParam.getAvatar_url());
        courier.setEmail(courierRegisterParam.getEmail());
        courier.setGender(courierRegisterParam.getGender());
        courier.setFull_name(courierRegisterParam.getFull_name());
        courier.setLanguage(courierRegisterParam.getLanguage());
        courier.setPhone(courierRegisterParam.getPhone());
        courier.setNickname(courierRegisterParam.getNickname());
        courier.setProvince(courierRegisterParam.getProvince());
        courier.setCity(courierRegisterParam.getCity());
        courierMapper.insert(courier);
        return courier.getId();
    }

    /**
     * 快递员信息获取
     * @param courierInformationParam
     * @return
     */
    public ResponseWrapper courierInformation(CourierInformationParam courierInformationParam){
        LambdaQueryWrapper<Courier> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Courier::getOpen_id,courierInformationParam.getOpen_id());
        Courier courier = courierMapper.selectOne(queryWrapper);
        if(courier!=null){
            CourierInformationResult courierInformationResult = new CourierInformationResult();
            courierInformationResult.setId(courier.getId());
            courierInformationResult.setFullName(courier.getFull_name());
            courierInformationResult.setPhone(courier.getPhone());
            courierInformationResult.setEmail(courier.getEmail());
            courierInformationResult.setNickname(courier.getNickname());
            courierInformationResult.setAvatarUrl(courier.getAvatar_url());
            courierInformationResult.setGender(courier.getGender());
            courierInformationResult.setCountry(courier.getCountry());
            courierInformationResult.setProvince(courier.getProvince());
            courierInformationResult.setLanguage(courier.getLanguage());
            courierInformationResult.setCity(courier.getCity());
            return ResponseWrapper.markSuccess("查询成功",courierInformationResult);
        }
        else{
            return ResponseWrapper.markError("此openId对应用户不存在");
        }
    }

    /**
     * 快递员物流查询
     * @param logisticsInformationParam
     * @return
     */
    public ResponseWrapper logisticsInformation(LogisticsInformationParam logisticsInformationParam){
        LambdaQueryWrapper<Express> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Express::getTracking_number,logisticsInformationParam.getTracking_number());
        Express express = expressMapper.selectOne(queryWrapper);
        if(express!=null){
            LogisticsInformationResult result = new LogisticsInformationResult();
            result.setDeliveryAddress(express.getDelivery_address());
            result.setRecipientAddress(express.getRecipient_address());
            result.setPickCourier(express.getPick_courier());
            result.setPresentOutlets(express.getPresent_outlets());
            result.setDelivered(express.getDelivered());
            result.setPickUp(express.getPick_up());
            result.setPrice(express.getPrice());
            result.setIsPayment(express.getIs_payment());
            result.setDeliveryCourier(express.getDelivery_courier());
            result.setBeginOutlets(express.getBegin_outlets());
            result.setEndOutlets(express.getEnd_outlets());
            return ResponseWrapper.markSuccess("查询成功",result);
        }
        else{
            return ResponseWrapper.markError("该快递单号对应快递不存在");
        }
    }

    /**
     * 快递员送件确认
     * @param expressReachConfirmParam
     * @return
     */
    public ResponseWrapper expressReachConfirm(ExpressReachConfirmParam expressReachConfirmParam){
        LambdaQueryWrapper<Express> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Express::getTracking_number,expressReachConfirmParam.getTracking_number());
        System.out.println(expressReachConfirmParam.getTracking_number());
        Express express = expressMapper.selectOne(queryWrapper);
        System.out.println(express);
        if(express != null){
            LambdaUpdateWrapper<Express> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Express::getTracking_number,express.getTracking_number());
            updateWrapper.set(Express::getDelivered,1);
            expressMapper.update(null,updateWrapper);
            return ResponseWrapper.markSuccess("妥投成功");
        }
        else{
            return ResponseWrapper.markError("妥投失败");
        }
    }

    /**
     * 快递员信息修改
     * @param courierInformationChangeParam
     * @return
     */
    public ResponseWrapper courierInformationChange(CourierInformationChangeParam courierInformationChangeParam){
        LambdaQueryWrapper<Courier> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Courier::getOpen_id,courierInformationChangeParam.getOpen_id());
        if(courierMapper.selectOne(queryWrapper) != null){
            LambdaUpdateWrapper<Courier> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(Courier::getOpen_id,courierInformationChangeParam.getOpen_id());
            updateWrapper.set(Courier::getFull_name,courierInformationChangeParam.getFull_name());
            updateWrapper.set(Courier::getPhone,courierInformationChangeParam.getPhone());
            updateWrapper.set(Courier::getEmail,courierInformationChangeParam.getEmail());
            updateWrapper.set(Courier::getNickname,courierInformationChangeParam.getNickname());
            updateWrapper.set(Courier::getAvatar_url,courierInformationChangeParam.getAvatar_url());
            updateWrapper.set(Courier::getGender,courierInformationChangeParam.getGender());
            updateWrapper.set(Courier::getCountry,courierInformationChangeParam.getCountry());
            updateWrapper.set(Courier::getProvince,courierInformationChangeParam.getProvince());
            updateWrapper.set(Courier::getLanguage,courierInformationChangeParam.getLanguage());
            updateWrapper.set(Courier::getCity,courierInformationChangeParam.getCity());
            courierMapper.update(null,updateWrapper);
            return ResponseWrapper.markSuccess("修改成功");
        }
        else{
            return ResponseWrapper.markError("该open_id对应快递员不存在");
        }
    }

    /**
     * 快递员查看网点任务
     * @param courierCheckOutletsTaskParam
     * @return
     */
    public ResponseWrapper courierCheckOutletsTask(CourierCheckOutletsTaskParam courierCheckOutletsTaskParam){
        LambdaQueryWrapper<OutletsCourier> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OutletsCourier::getCourier,courierCheckOutletsTaskParam.getId());
        queryWrapper.eq(OutletsCourier::getConfirmed,1);
        if (outletsCourierMapper.selectOne(queryWrapper)==null){
            return ResponseWrapper.markError("网点未通过改快递员申请");
        }
        OutletsCourier outletsCourier = outletsCourierMapper.selectOne(queryWrapper);

        Long outlets = outletsCourier.getOutlets();
        LambdaQueryWrapper<Express> expressQueryWrapper = new LambdaQueryWrapper<>();
        expressQueryWrapper.eq(Express::getEnd_outlets,outlets);
        expressQueryWrapper.eq(Express::getDelivered,0);
        List<Express> deliverExpresses = expressMapper.selectList(expressQueryWrapper);

        List<CourierCheckOutletsTaskResult> deliverResults = JSON.parseArray(JSON.toJSONString(deliverExpresses)).toJavaList(CourierCheckOutletsTaskResult.class);

        expressQueryWrapper.clear();
        expressQueryWrapper.eq(Express::getPick_up,0);
        expressQueryWrapper.eq(Express::getBegin_outlets,outlets);
        List<Express> pickUpExpresses = expressMapper.selectList(expressQueryWrapper);
        List<CourierCheckOutletsTaskResult> pickUpResults = JSON.parseArray(JSON.toJSONString(pickUpExpresses)).toJavaList(CourierCheckOutletsTaskResult.class);

        return ResponseWrapper.markSuccess("查找成功",deliverResults.addAll(pickUpResults));
    }

    /**
     * 快递员接单
     * @param courierAcceptTaskParam
     * @return
     */
    public ResponseWrapper courierAcceptTask(CourierAcceptTaskParam courierAcceptTaskParam){
        LambdaUpdateWrapper<Express> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Express::getTracking_number,courierAcceptTaskParam.getTracking_number());
        if(courierAcceptTaskParam.getStatus()==0){
            updateWrapper.set(Express::getDelivery_courier,courierAcceptTaskParam.getCourier_id());
        }
        else if(courierAcceptTaskParam.getStatus()==1){
            updateWrapper.set(Express::getPick_courier,courierAcceptTaskParam.getCourier_id());
        }
        expressMapper.update(null,updateWrapper);
        return ResponseWrapper.markSuccess("接单成功");
    }



    /**
     * 快递员查看负责过的快递
     * @param courierCheckExpressParam
     * @return
     */
    public ResponseWrapper courierCheckExpress(CourierCheckExpressParam courierCheckExpressParam){
        LambdaQueryWrapper<Express> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Express::getPick_courier,courierCheckExpressParam.getId())
                .or().eq(Express::getDelivery_courier,courierCheckExpressParam.getId());
        List<Express> expresses = expressMapper.selectList(queryWrapper);
        List<CourierCheckExpressResult> results = JSON.parseArray(JSON.toJSONString(expresses)).toJavaList(CourierCheckExpressResult.class);
        return ResponseWrapper.markSuccess("查询成功",results);
    }

    /**
     * 快递员确认取件
     * @param expressGetConfirmParam
     * @return
     */
    public ResponseWrapper expressGetConfirm(ExpressGetConfirmParam expressGetConfirmParam){
        LambdaQueryWrapper<Express> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Express::getTracking_number,expressGetConfirmParam.getTracking_number());
        if(expressMapper.selectOne(queryWrapper)==null){
            return ResponseWrapper.markError("该快递单号对应快递不存在");
        }
        LambdaUpdateWrapper<Express> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Express::getTracking_number,expressGetConfirmParam.getTracking_number());
        updateWrapper.set(Express::getPick_up,1);
        expressMapper.update(null,updateWrapper);
        return ResponseWrapper.markSuccess("确认取件成功");
    }

    /**
     * 快递员申请网点
     * @param courierApplyOutletsParam
     * @return
     */
    public ResponseWrapper courierApplyOutlets(CourierApplyOutletsParam courierApplyOutletsParam){
        OutletsCourier outletsCourier = new OutletsCourier();
        outletsCourier.setCourier(courierApplyOutletsParam.getCourier_id());
        outletsCourier.setOutlets(courierApplyOutletsParam.getOutlets_id());
        outletsCourier.setIs_delete(0);
        outletsCourier.setConfirmed(0);
        outletsCourierMapper.insert(outletsCourier);
        return ResponseWrapper.markSuccess("申请已经发送");
    }

    public ResponseWrapper userLogin(String code) throws IOException {
        //登录凭证校验。通过 wx.login 接口获得临时登录凭证 code 后传到开发者服务器调用此接口完成登录流程
        JSONObject code2Session = courierWechatService.getCode2Session(code);
        Integer errorCode =code2Session.getInteger("errcode");

        //errcode为0表示请求成功
        if(errorCode==0){
            //将获得的openid 返回
            String openid=code2Session.getString("openid");

            LambdaQueryWrapper<Courier> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Courier::getOpen_id,openid);
            Courier courier=courierMapper.selectOne(queryWrapper);
            if(courier!=null){
                return ResponseWrapper.markSuccess("登录成功",ImmutableMap.of("openId",openid,"id",courier.getId()));
            }
            Courier newCourier=new Courier();
            newCourier.setOpen_id(openid);
            courierMapper.insert(newCourier);
            return ResponseWrapper.markSuccess("新用户注册成功",ImmutableMap.of("openId",openid,"id",newCourier.getId()));
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
}
