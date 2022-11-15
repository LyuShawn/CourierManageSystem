package com.CourierManageSystem.backend.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.log4j.Log4j2;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Log4j2
public class UserWechatService {

    @Value("${mini-app-user.appid}")
    private String APPID;
    @Value("${mini-app-user.secret}")
    private String SECRET;
    private String messageUrl="https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=";
    private static final String AUTHORIZATION_CODE = "authorization_code";
    private static final String GRANT_TYPE="client_credential";

    public JSONObject getCode2Session(String code) throws IOException {
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        OkHttpClient okHttpClient=new OkHttpClient();
        RequestBody body=new FormBody.Builder()
                .add("appid",APPID)
                .add("secret",SECRET)
                .add("js_code",code)
                .add("grant_type",AUTHORIZATION_CODE).build();
        Request request=new Request.Builder()
                .url(requestUrl)
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        Response response=call.execute();
        JSONObject jsonObject= (JSONObject) JSON.parse(response.body().string());
        if(!jsonObject.containsKey("openid")){
            jsonObject.put("openid","");
        }
        if(!jsonObject.containsKey("session_key")){
            jsonObject.put("session_key","");
        }
        if(!jsonObject.containsKey("errcode")){
            jsonObject.put("errcode", 0);
        }
        if (!jsonObject.containsKey("errmsg")){
            jsonObject.put("errmsg","登录成功");
        }
        return  jsonObject;
    }

    public JSONObject getAccessToken() throws IOException {
        String requestUrl ="https://api.weixin.qq.com/cgi-bin/token";
        OkHttpClient okHttpClient=new OkHttpClient();
        HttpUrl.Builder httpBuilder=HttpUrl.parse(requestUrl).newBuilder();
        httpBuilder.addQueryParameter("grant_type",GRANT_TYPE);
        httpBuilder.addQueryParameter("appid",APPID);
        httpBuilder.addQueryParameter("secret",SECRET);
        Request request=new Request.Builder()
                .url(httpBuilder.build())
                .build();
        Response response = okHttpClient.newCall(request).execute();
        JSONObject jsonObject= (JSONObject) JSON.parse(response.body().string());
        return jsonObject;
    }

//    public JSONObject sendMessage(String openId, RecordDO recordDO) throws IOException {
//        //accessToken
//        String accessToken = new WechatService().getAccessToken().getString("access_token");
//        String requestUrl=messageUrl+accessToken;
//        OkHttpClient okHttpClient=new OkHttpClient();
//        HttpUrl.Builder httpBuilder=HttpUrl.parse(requestUrl).newBuilder();
//        httpBuilder.addQueryParameter("access_token",accessToken);
//        JSONObject postBody=new JSONObject();
//        postBody.put("touser",openId);
//        postBody.put("template_id","f1NfmhmeOqgzI6qSDuOEZ8yiKVrVWcwXg0opQn5FKlU");
//        postBody.put("page","pages/index/index");
//
//        JSONObject data=new JSONObject();
//        data.put("date1", ImmutableMap.of("value",recordDO.getFormalDate()));
//        data.put("number2",ImmutableMap.of("value", Math.max(recordDO.getSurplusDate(), 0)));
//        data.put("thing3",ImmutableMap.of("value", recordDO.getName()));
//        postBody.put("data",data);
//        /*需要将数据格式设置为application/json,否则会出现47001----data format error rid: 6084cf6b-7c22f070-136ea237*/
//        MediaType mediaType=MediaType.parse("application/json;charset=utf-8");
//        RequestBody body=RequestBody.create(mediaType,String.valueOf(postBody));
//        Request request=new Request.Builder()
//                .url(httpBuilder.build())
//                .post(body)
//                .build();
//        Call call = okHttpClient.newCall(request);
//        Response response=call.execute();
//        return (JSONObject) JSON.parse(Objects.requireNonNull(response.body()).string());
//    }

    public JSONObject getUserInfo(String openid) throws IOException {
        String requestUrl ="https://api.weixin.qq.com/cgi-bin/user/info";
        OkHttpClient okHttpClient=new OkHttpClient();
        HttpUrl.Builder httpBuilder=HttpUrl.parse(requestUrl).newBuilder();
        httpBuilder.addQueryParameter("access_token",getAccessToken().getString("access_token"));
        httpBuilder.addQueryParameter("openid",openid);
        Request request=new Request.Builder()
                .url(httpBuilder.build())
                .build();
        Response response = okHttpClient.newCall(request).execute();
        JSONObject jsonObject= (JSONObject) JSON.parse(response.body().string());
        return jsonObject;
    }

    public JSONObject getUserPortrait(String begin_date,String end_date) throws IOException {
        String accessToken =  new UserWechatService().getAccessToken().getString("access_token");
        String requestUrl="https://api.weixin.qq.com/datacube/getweanalysisappiduserportrait";
        OkHttpClient okHttpClient=new OkHttpClient();
        HttpUrl.Builder httpBuilder=HttpUrl.parse(requestUrl).newBuilder();
        httpBuilder.addQueryParameter("access_token",accessToken);
        JSONObject data=new JSONObject();
        data.put("begin_date",begin_date);
        data.put("end_date",end_date);
        /*需要将数据格式设置为application/json,否则会出现47001----data format error rid: 6084cf6b-7c22f070-136ea237*/
        MediaType mediaType=MediaType.parse("application/json;charset=utf-8");
        RequestBody body=RequestBody.create(mediaType,String.valueOf(data));
        Request request=new Request.Builder()
                .url(httpBuilder    .build())
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        Response response=call.execute();
        JSONObject jsonObject= (JSONObject) JSON.parse(response.body().string());
        return jsonObject;
    }

    public JSONObject getDailyVisitTrend(String begin_date,String end_date) throws IOException {
        String accessToken = new UserWechatService().getAccessToken().getString("access_token");
        String requestUrl="https://api.weixin.qq.com/datacube/getweanalysisappiddailyvisittrend";
        OkHttpClient okHttpClient=new OkHttpClient();
        HttpUrl.Builder httpBuilder=HttpUrl.parse(requestUrl).newBuilder();
        httpBuilder.addQueryParameter("access_token",accessToken);
        JSONObject data=new JSONObject();
        data.put("begin_date",begin_date);
        data.put("end_date",end_date);
        /*需要将数据格式设置为application/json,否则会出现47001----data format error rid: 6084cf6b-7c22f070-136ea237*/
        MediaType mediaType=MediaType.parse("application/json;charset=utf-8");
        RequestBody body=RequestBody.create(mediaType,String.valueOf(data));
        Request request=new Request.Builder()
                .url(httpBuilder.build())
                .post(body)
                .build();
        Call call = okHttpClient.newCall(request);
        Response response=call.execute();
        return (JSONObject) JSON.parse(response.body().string());
    }
}
