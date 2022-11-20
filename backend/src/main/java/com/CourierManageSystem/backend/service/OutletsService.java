package com.CourierManageSystem.backend.service;

import com.CourierManageSystem.backend.entity.Courier;
import com.CourierManageSystem.backend.entity.Express;
import com.CourierManageSystem.backend.entity.Outlets;
import com.CourierManageSystem.backend.entity.OutletsCourier;
import com.CourierManageSystem.backend.mapper.CourierMapper;
import com.CourierManageSystem.backend.mapper.ExpressMapper;
import com.CourierManageSystem.backend.mapper.OutletsCourierMapper;
import com.CourierManageSystem.backend.mapper.OutletsMapper;
import com.CourierManageSystem.backend.model.OutletsParamAndResult.*;
import com.CourierManageSystem.backend.util.GetGeocoding;
import com.CourierManageSystem.backend.util.ResponseWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class OutletsService {

    @Autowired
    private OutletsMapper outletsMapper;
    @Autowired
    private OutletsCourierMapper outletsCourierMapper;
    @Autowired
    private CourierMapper courierMapper;
    @Autowired
    private ExpressMapper expressMapper;


    /**
     * 网点注册
     * @param outletsRegisterParam
     * @return
     */
    public ResponseWrapper register(OutletsRegisterParam outletsRegisterParam)
    {

        System.out.println("网点注册参数为"+outletsRegisterParam.toString());
        Map<String, Object> map=new HashMap<>();
        map.put("username",outletsRegisterParam.getUsername());
        map.put("is_delete",0);
        List<Outlets> outlets=outletsMapper.selectByMap(map);
        if(outlets.size()!=0)
            return ResponseWrapper.markError("已存在");
        Outlets outlet=new Outlets();

        outlet.setUsername(outletsRegisterParam.getUsername());
        outlet.setPwd(outletsRegisterParam.getPwd());
        outlet.setNickname(outletsRegisterParam.getNickname());
        outlet.setPhone(outletsRegisterParam.getPhone());
        outlet.setAddress(outletsRegisterParam.getAddress());
        outlet.setPrincipal_name(outletsRegisterParam.getPrincipal_name());
        outlet.setPrincipal_identity_id(outletsRegisterParam.getPrincipal_identity_id());
        outlet.setLocation(GetGeocoding.getGeocoding(outletsRegisterParam.getAddress()));
        outletsMapper.insert(outlet);
        System.out.println("提交成功\n"+outlet.toString());
        return ResponseWrapper.markSuccess("请等待管理员同意申请");
    }
    /**
     * 获取单个网点信息
     * @return
     */
    public ResponseWrapper getOne(OutletsGetOneParam outletsGetOneParam){
        Map<String, Object> map=new HashMap<>();
        map.put("username",outletsGetOneParam.getUsername());
        map.put("pwd",outletsGetOneParam.getPwd());
        map.put("is_delete",0);
        List<Outlets> outlets=outletsMapper.selectByMap(map);
        if(outlets.size()==0)
            return ResponseWrapper.markError("账号不存在或密码错误");
        Outlets outlet= outlets.get(0);
        OutletsGetOneResult outletsGetOneResult = new OutletsGetOneResult();
        outletsGetOneResult.setId(outlet.getId());
        outletsGetOneResult.setUsername(outlet.getUsername());
        outletsGetOneResult.setNickname(outlet.getNickname());
        outletsGetOneResult.setPhone(outlet.getPhone());
        outletsGetOneResult.setPrincipal_identity_id(outlet.getPrincipal_identity_id());
        outletsGetOneResult.setPrincipal_name(outlet.getPrincipal_name());
        outletsGetOneResult.setAddress(outlet.getAddress());
        outletsGetOneResult.setLocation(outlet.getLocation());
        outletsGetOneResult.setConfirmed(outlet.getConfirmed());
        return ResponseWrapper.markSuccess("获取成功",outletsGetOneResult);
    }


    /**
     * 查看快递员申请表
     * @return
     */
    public ResponseWrapper showCourierApplyList(OutletsCourierApplyParam outletsCourierApplyParam) {
        Map<String, Object> map=new HashMap<>();
        map.put("outlets",outletsCourierApplyParam.getId());
        map.put("is_delete",0);
        map.put("confirmed",0);
        List<OutletsCourier> outletsCouriers = outletsCourierMapper.selectByMap(map);
        if(outletsCouriers.size()==0)
            return ResponseWrapper.markError("暂无快递员");
        List<Courier> couriers = new ArrayList<>();
        for(OutletsCourier x:outletsCouriers){
            map=new HashMap<>();
            map.put("id",x.getCourier());
            map.put("is_delete",0);
            couriers.add(courierMapper.selectByMap(map).get(0));
        }
        return ResponseWrapper.markSuccess("获取成功",couriers);
    }

    /**
     * 同意快递员申请
     * @return
     */
    public ResponseWrapper courierIsJoin(OutletsCourierIsJoinParam outletsCourierIsJoinParam) {
        Map<String, Object> map=new HashMap<>();
        map.put("outlets",outletsCourierIsJoinParam.getOutlets_id());
        map.put("courier",outletsCourierIsJoinParam.getCourier());
        map.put("is_delete",0);
        List<OutletsCourier> outletsCouriers=outletsCourierMapper.selectByMap(map);
        if(outletsCouriers.size()==0)
            return ResponseWrapper.markError("网点id或快递员id不存在");
        OutletsCourier outletsCourier = outletsCouriers.get(0);
        if(outletsCourier.getConfirmed() == 1)
            return ResponseWrapper.markError("快递员已存在");
        outletsCourier.setConfirmed(1);
        outletsCourierMapper.updateById(outletsCourier);
        return ResponseWrapper.markSuccess("申请成功");
    }

    /**
     * 查看所有快递
     * @return
     */
    public ResponseWrapper showAllExpress(OutletsExpressAllParam outletsExpressAllParam) {
        Map<String, Object> map=new HashMap<>();
        map.put("now_outlets",outletsExpressAllParam.getOutlets_id());
        map.put("is_delete",0);
        List<Express> expresses= expressMapper.selectByMap(map);
        return ResponseWrapper.markSuccess("申请成功",expresses);
    }

    /**
     * 快递入库
     * @return
     */
    public ResponseWrapper getExpress(OutletsGetExpressParam outletsGetExpressParam) {
        Map<String, Object> map=new HashMap<>();
        map.put("is_delete",0);
        map.put("id",outletsGetExpressParam.getOutlets_id());
        List<Outlets> outlets= outletsMapper.selectByMap(map);
        if(outlets.size()==0)
            return ResponseWrapper.markError("网点不存在");
        map=new HashMap<>();
        map.put("is_delete",0);
        map.put("tracking_number",outletsGetExpressParam.getTracking_number());
        List<Express> expresses= expressMapper.selectByMap(map);
        if(expresses.size()==0)
            return ResponseWrapper.markError("快递不存在");
        Express express = expresses.get(0);
        if(express.getNow_outlets()==outletsGetExpressParam.getOutlets_id())
            return ResponseWrapper.markError("快递已存在该网点仓库中");
        express.setNow_outlets(outletsGetExpressParam.getOutlets_id());
        express.setPresent_outlets(express.getPresent_outlets()+"-"+outletsGetExpressParam.getOutlets_id());
        expressMapper.updateById(express);
        return ResponseWrapper.markSuccess("入库成功");
    }

    /**
     * 修改网点信息
     * @return
     */
    public ResponseWrapper modifyInf(OutletsModifyInfParam outletsModifyInfParam) {
        System.out.println("网点修改参数为"+outletsModifyInfParam.toString());
        Map<String, Object> map = new HashMap<>();
        map.put("is_delete", 0);
        map.put("id", outletsModifyInfParam.getId());
        List<Outlets> outlets = outletsMapper.selectByMap(map);
        if (outlets.size() == 0)
            return ResponseWrapper.markError("网点不存在");
        Outlets outlet = outlets.get(0);
        outlet.setUsername(outletsModifyInfParam.getUsername());
        outlet.setPwd(outletsModifyInfParam.getPwd());
        outlet.setNickname(outletsModifyInfParam.getNickname());
        outlet.setPhone(outletsModifyInfParam.getPhone());
        outlet.setAddress(outletsModifyInfParam.getAddress());
        outlet.setLocation(GetGeocoding.getGeocoding(outletsModifyInfParam.getAddress()));
        outlet.setPrincipal_name(outletsModifyInfParam.getPrincipal_name());
        outlet.setPrincipal_identity_id(outletsModifyInfParam.getPrincipal_identity_id());
        outletsMapper.updateById(outlet);
        return ResponseWrapper.markSuccess("修改成功");
    }

    /**
     * 查看网点管理员
     * @return
     */
    public ResponseWrapper showCouriers(OutletsCourierApplyParam outletsCourierApplyParam) {
        Map<String, Object> map=new HashMap<>();
        map.put("outlets",outletsCourierApplyParam.getId());
        map.put("is_delete",0);
        map.put("confirmed",1);
        List<OutletsCourier> outletsCouriers = outletsCourierMapper.selectByMap(map);
        if(outletsCouriers.size()==0)
            return ResponseWrapper.markError("暂无快递员");
        List<Courier> couriers = new ArrayList<>();
        for(OutletsCourier x:outletsCouriers){
            map=new HashMap<>();
            map.put("id",x.getCourier());
            map.put("is_delete",0);
            if(courierMapper.selectByMap(map).size()!=0)
                couriers.add(courierMapper.selectByMap(map).get(0));
        }
        return ResponseWrapper.markSuccess("成功",couriers);
    }

    /**
     * 删除网点管理员
     * @return
     */
    public ResponseWrapper delCourier(OutletsCourierIsJoinParam outletsCourierIsJoinParam) {

//        Map<String, Object> map=new HashMap<>();
//        map.put("outlets",outletsCourierIsJoinParam.getOutlets_id());
//        map.put("courier",outletsCourierIsJoinParam.getCourier());
//        map.put("is_delete",0);
//        map.put("confirmed",1);
//        List<OutletsCourier> outletsCouriers = outletsCourierMapper.selectByMap(map);
//        if(outletsCouriers.size()==0)
//            return ResponseWrapper.markError("快递员或网点不存在");
//        OutletsCourier outletsCourier = outletsCouriers.get(0);
//        outletsCourier.setIs_delete(1);

        int r=outletsCourierMapper.deleteByMap(ImmutableMap.of("outlets",outletsCourierIsJoinParam.getOutlets_id(),"courier",outletsCourierIsJoinParam.getCourier()));
        return r>0?ResponseWrapper.markSuccess("删除成功"):ResponseWrapper.markError("删除失败");
    }
}
