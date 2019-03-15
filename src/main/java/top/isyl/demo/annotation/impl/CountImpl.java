package top.isyl.demo.annotation.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import top.isyl.demo.annotation.Count;
import top.isyl.demo.entity.TCounter;
import top.isyl.demo.service.ITCounterService;

import java.lang.reflect.Method;

/**
 * @Author huangyunlong
 * @Date 2019/3/11
 */
@Aspect
@Component
public class CountImpl {

    @Autowired
    private ITCounterService counterService;


    /**
     * @Pointcut
     * @Pointcut定义一个切点
     */
    @Pointcut("@annotation(top.isyl.demo.annotation.Count)")
    private void cou() {

    }


    /**
     * @After
     * @After最后执行
     */
    @After("cou()")
    public void after(JoinPoint joinPoint) {

        /**
         *  带参数注解
         */
        try {
            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            String value ;
            for(Method method : methods) {
                if(method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if(clazzs.length == arguments.length) {
                        Count annotation = method.getAnnotation(Count.class);
                        value = annotation.value();
                        System.out.println("type:"+value);
                        if(!StringUtils.isEmpty(value)){
                            TCounter counter = counterService.getOne(new QueryWrapper<TCounter>().eq("type", value));
                            if (ObjectUtils.isEmpty(counter)){
                                counter = new TCounter();
                                counter.setType(value);
                                counter.setContent(annotation.content());
                                counterService.save(counter);
                            }else{
                                counter.setCount(counter.getCount()+1);
                                counterService.updateById(counter);
                            }
                        }
                        break;
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
