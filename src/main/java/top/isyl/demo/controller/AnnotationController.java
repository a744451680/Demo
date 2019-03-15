package top.isyl.demo.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.isyl.demo.annotation.Count;
import top.isyl.demo.annotation.MyAnnotation;
import top.isyl.demo.entity.TCounter;
import top.isyl.demo.service.ITCounterService;

/**
 *  自定义注解测试类
 * @Author huangyunlong
 * @Date 2019/3/7
 */
@RestController
@RequestMapping("/annotation")
@Api(value = "自定义注解测试Controller",tags = {"自定义注解"})
public class AnnotationController {

    @Autowired
    private ITCounterService counterService;


    /**
     * 其实一个自定义注解的实现只需要3个类，
     *      一个声明注解，
     *      一个注解的具体实现，
     *      一个使用注解的实例即可。
     */
    @GetMapping("/test")
    @MyAnnotation
    @ApiOperation(value = "测试自定义注解")
    public Object test(){
        System.out.println("******33333*******");
        return "success";
    }


    /**
     * 带参数 注解（计数器）
     */
    @GetMapping("/count")
    @Count(value = "test" , content = "测试注解")
    @ApiOperation(value = "测试自定义注解：计数器(带参数)")
    public Object count(){

        TCounter one = counterService.getOne(new QueryWrapper<TCounter>().eq("type", "test"));
        System.out.println("******测试自定义注解*******"+ JSON.toJSONString(one));
        return one==null?0:one.getCount();
    }

}
