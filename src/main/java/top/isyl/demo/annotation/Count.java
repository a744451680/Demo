package top.isyl.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 带参数注解：统计访问次数
 *
 * @Author huangyunlong
 * @Date 2019/3/11
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Count {

    //类型
    String value() default "";
    //描述
    String content() default "";

}
