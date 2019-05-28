package top.isyl.demo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import top.isyl.demo.entity.TAccessTokenInfo;
import top.isyl.demo.mapper.TAccessTokenInfoMapper;
import top.isyl.demo.service.ITAccessTokenInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.isyl.demo.util.DateUtil;
import top.isyl.demo.util.HttpRequestUtil;
import top.isyl.demo.util.HttpsClientUtil;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hyl
 * @since 2019-04-22
 */
@Service
@Slf4j
public class TAccessTokenInfoServiceImpl extends ServiceImpl<TAccessTokenInfoMapper, TAccessTokenInfo> implements ITAccessTokenInfoService {

    @Value("${wx.appId}")
    private String appId;

    @Value("${wx.appSecret}")
    private String appSecret;


    /**
     * 获取AccessToken
     */
    @Override
    public void updateAccessToken() {
//        if(true)return ;
        while (true) {
            log.info("获取access_token");
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + this.appId + "&secret=" + this.appSecret;
//            String response = HttpsClientUtil.httpsRequest(url, "GET", null);
            String response = HttpRequestUtil.get(url);

            log.info("response:" + response);
            String accessToken = "";
            JSONObject obj = JSON.parseObject(response);
            if (ObjectUtil.isNotNull(obj)) {
                if (ObjectUtil.isNotNull(obj.get("access_token"))) {
                    accessToken = obj.get("access_token").toString();
                    if (ObjectUtil.isNotNull(accessToken)) {
                        log.info("获取access_token结果：{}", accessToken);
                        TAccessTokenInfo accessTokenInfo = new TAccessTokenInfo();
                        accessTokenInfo.setTokenId("isyl");
                        accessTokenInfo.setExpiresIn("7200");
                        accessTokenInfo.setRegTime(DateUtil.getDateToStringMilli(new Date()));
                        accessTokenInfo.setAccessToken(accessToken);
                        try {
                            this.updateById(accessTokenInfo);
                            log.info("access_token更新成功");
                            return;
                        } catch (Exception e) {
                            log.info("更新AccessToken时异常e={}", e);
                        }
                    }else{
                        try {
                            log.info("获取Access_token时Access_token=null等待60秒");
                            Thread.sleep(60000);
                        } catch (InterruptedException e) {
                            log.info("获取Access_token时Access_token=null等待60秒 异常e={}", e);
                        }
                    }
                } else {
                    if (ObjectUtil.isNotNull(obj.get("errcode"))){
                        // 错误码
                        String errorCode = obj.get("errcode").toString();
                        switch (errorCode) {
                            case "0":
                                log.info("微信返回：{}  系统繁忙，此时请开发者稍候再试", errorCode);
                                break;
                            case "-1":
                                log.info("微信返回：{}  请求成功", errorCode);
                                break;
                            case "40001":
                                log.info("微信返回：{}  AppSecret错误或者AppSecret不属于这个公众号，请开发者确认AppSecret的正确性", errorCode);
                                break;
                            case "40002":
                                log.info("微信返回：{}  请确保grant_type字段值为client_credential", errorCode);
                                break;
                            case "40164":
                                log.info("微信返回：{}  调用接口的IP地址不在白名单中，请在接口IP白名单中进行设置", errorCode);
                                break;
                            default:
                                log.info("微信返回：{}  错误原因微信官方未给解释", errorCode);
                        }
                    }
                }
            } else {
                try {
                    log.info("获取Access_token时null等待60秒");
                    Thread.sleep(60000);
                } catch (InterruptedException e) {
                    log.info("获取Access_token时null等待60秒 异常e={}", e);
                }
            }
        }
    }
}
