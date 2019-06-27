/*
package top.isyl.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.isyl.demo.entity.AjaxResult;

@Slf4j
@RestController
@RequestMapping("/redis")
@Api(value = "缓存Conller", tags = "缓存")
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/test")
    @ApiOperation("测试")
    public AjaxResult testRedis(){

        String str = "123456789"+System.currentTimeMillis();
        String s2 = "k2";

//        两种序列化策略不同
//        redisTemplate  是jdk策略
//        stringRedisTemplate 是string策略
        redisTemplate.opsForValue().set("k1",str);
        stringRedisTemplate.opsForValue().set(s2,str);

        System.out.println(redisTemplate.opsForValue().get("k1"));
        System.out.println(stringRedisTemplate.opsForValue().get(s2));

        Object k1 = redisTemplate.opsForValue().get("k1");

        return new AjaxResult().success().res(k1);
    }
}
*/
