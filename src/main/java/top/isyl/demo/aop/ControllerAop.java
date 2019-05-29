package top.isyl.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 控制器切面
 *
 * @author Administrator
 * @createtime
 */
@Slf4j
@Aspect
@Component
public class ControllerAop {

  /*  @Pointcut("execution(public * top.isyl.demo.controller.*.*(..))")
    public void pointCutController() {
    }

    *//**
     * 拦截器具体实现
     *
     * @param pjp 切点 所有控制器，监控响应时间
     * @return R  结果包装
     *//*
    @Around("pointCutController()")
    public Object methodRHandler(ProceedingJoinPoint pjp) throws Throwable {
        return methodHandler(pjp);
    }

    *//**
     * 定义切点
     *//*
    @Pointcut("execution(public * top.isyl.demo.mapper.*.*(..))")
    public void pointCutMapper() {
    }

    *//**
     * 拦截器具体实现
     *
     * @param pjp 切点 所有mapper 监控sql性能
     * @return R  结果包装
     *//*
    @Around("pointCutMapper()")
    public Object methodPageHandler(ProceedingJoinPoint pjp) throws Throwable {
        return methodHandler(pjp);
    }

    private Object methodHandler(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();

        //获取上一个请求保存的RequestAttributes
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(attributes != null){
            //获取request对象
            HttpServletRequest request = attributes.getRequest();

            log.info("请求地址      : " + request.getRequestURL().toString());
            log.info("请求方法      : " + request.getMethod());
            log.info("请求客户端ip  : " + request.getRemoteAddr());
            log.info("方法名称      : " + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
            Map<String, String[]> parameterMap = request.getParameterMap();
            StringBuffer sr = new StringBuffer();
            parameterMap.forEach((key, values) -> {
                sr.append(key).append(":");
                for (int i = 0; i < values.length; i++) {
                    sr.append(values[i]).append(" ");
                }
                sr.append("\t");
            });
            log.info("请求参数      :  "+sr.toString());

            Object result;

            // 使目标方法执行
            result = pjp.proceed();
            log.info(pjp.getSignature() + "调用时间:" + (System.currentTimeMillis() - startTime));

            return result;
        }
        return pjp.proceed();
    }*/
}
