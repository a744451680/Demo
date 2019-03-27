package top.isyl.demo.annotation.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
/**
 * @Author huangyunlong
 * @Date 2019/3/7
 */
/**
 * @Aspect
 * @Aspect作用是把当前类标识为一个切面供容器读取
 */
@Aspect
@Component
public class MyAnnotationImpl {


    /**
     * @Pointcut
     * @Pointcut定义一个切点
     */
    @Pointcut("@annotation(top.isyl.demo.annotation.MyAnnotation)")
    private void cut() {
        System.out.println("3");
    }

    /**
     * @Around
     * @Around，环绕增强
     */
    @Around("cut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("1");
        try {
            // 使目标方法执行
            joinPoint.proceed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("4");
    }


    /**
     * @Before
     * @Before标识一个前置增强方法，相当于BeforeAdvice的功能
     */
    @Before("cut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("2");


    }

    /**
     * @After
     * @After最后执行
     */
    @After("cut()")
    public void after() {
        System.out.println("5");
    }
}
