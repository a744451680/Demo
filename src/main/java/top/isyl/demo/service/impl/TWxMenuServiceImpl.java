/*
package top.isyl.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import top.isyl.demo.entity.TAccessTokenInfo;
import top.isyl.demo.entity.TWxMenu;
import top.isyl.demo.mapper.TWxMenuMapper;
import top.isyl.demo.service.ITAccessTokenInfoService;
import top.isyl.demo.service.ITWxMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.isyl.demo.util.HttpsClientUtil;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

*/
/**
 * <p>
 * 微信菜单表 服务实现类
 * </p>
 *
 * @author hyl
 * @since 2019-04-22
 *//*

@Service
@Slf4j
public class TWxMenuServiceImpl extends ServiceImpl<TWxMenuMapper, TWxMenu> implements ITWxMenuService {

    @Resource
    ITAccessTokenInfoService accessTokenInfoService;

    @Autowired
    RedisTemplate redisTemplate;

    */
/**
     * 创建微信菜单
     *//*

    @Override
    public void createWxMenux() {
        //获取数据库中菜单json
        TWxMenu one = this.getOne(new QueryWrapper<>());
        log.info("one:" + one);
        String wxMenuJson = one.getWxMenuJson();

        //缓存里有 用缓存的  没有用数据库的
        String accessTokenRedisString = (String) redisTemplate.opsForValue().get("accessToken");
        if (StringUtils.isBlank(accessTokenRedisString)) {
            accessTokenRedisString = accessTokenInfoService.getOne(new QueryWrapper<>()).getAccessToken();
            redisTemplate.opsForValue().set("accessToken", accessTokenRedisString, 30 * 60, TimeUnit.SECONDS);
        }
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessTokenRedisString;
        log.info("url:" + url);
        log.info("wxMenuJson:" + wxMenuJson);

        String response = HttpsClientUtil.httpsRequest(url, "POST", wxMenuJson);
        log.info("post:" + response);
    }

    */
/**
     * 查询微信菜单
     *//*

    @Override
    public String getWxMenux() {

        //缓存里有 用缓存的  没有用数据库的
        String accessTokenRedisString = (String) redisTemplate.opsForValue().get("accessToken");
        if (StringUtils.isBlank(accessTokenRedisString)) {
            accessTokenRedisString = accessTokenInfoService.getOne(new QueryWrapper<>()).getAccessToken();
            redisTemplate.opsForValue().set("accessToken", accessTokenRedisString, 30 * 60, TimeUnit.SECONDS);
        }
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessTokenRedisString;
        log.info("url:" + url);

        String response = HttpsClientUtil.httpRequest(url, "GET", null);
        log.info("get:" + response);
        return response;
    }

    */
/**
     * 删除微信菜单
     *//*

    @Override
    public void delectWxMenu() {

        //缓存里有 用缓存的  没有用数据库的
        String accessTokenRedisString = (String) redisTemplate.opsForValue().get("accessToken");
        if (StringUtils.isBlank(accessTokenRedisString)) {
            accessTokenRedisString = accessTokenInfoService.getOne(new QueryWrapper<>()).getAccessToken();
            redisTemplate.opsForValue().set("accessToken", accessTokenRedisString, 30 * 60, TimeUnit.SECONDS);
        }
        String url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessTokenRedisString;
        log.info("url:" + url);

        String response = HttpsClientUtil.httpsRequest(url, "GET", null);
        log.info("get:" + response);
        return;
    }
}
*/
