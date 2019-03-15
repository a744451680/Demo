package top.isyl.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 定时任务 配置
 * @Author huangyunlong
 * @Date 2019/3/1
 */
@Configuration
@EnableScheduling
public class SchedulerConfig implements SchedulingConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerConfig.class);
    /**
     * Springboot本身默认的执行方式是串行执行，也就是说无论有多少task，都是一个线程串行执行，并行需手动配置
     * 继承SchedulingConfigurer类并重写其方法
     * @param taskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        logger.info("【定时任务配置】");
        taskRegistrar.setScheduler(threadPoolTaskScheduler());
    }


    @Bean(name = "taskScheduler", destroyMethod = "shutdown")
    public ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        ThreadPoolTaskScheduler poolTaskScheduler = new ThreadPoolTaskScheduler();
//        java.lang.Runtime.availableProcessors() //方法返回到Java虚拟机的可用的处理器数量
//        poolTaskScheduler.setPoolSize(Runtime.getRuntime().availableProcessors() * 5);
        poolTaskScheduler.setPoolSize(5);

        return poolTaskScheduler;
    }

}
