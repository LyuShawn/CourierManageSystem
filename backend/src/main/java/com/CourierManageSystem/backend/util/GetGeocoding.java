package com.CourierManageSystem.backend.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.http.HttpHeaders;
@Data
public class GetGeocoding {
    public static String getGeocoding(String address)
    {
        String url = "https://restapi.amap.com/v3/geocode/geo?address="+address+"&key=8b2cb41a1f54d92aa09d497cb1ea42a8";
        HttpHeaders headers = new HttpHeaders();
        JSONObject parse = (JSONObject) JSON.parse(RestUtil.doGetRequest(url, headers));
        System.out.println("地址"+address+"的信息为"+parse.toJSONString());
        JSONArray jsarr=parse.getJSONArray("geocodes");
        String Geocoding= jsarr.getJSONObject(0).get("location").toString();
        return Geocoding;
    }
}
