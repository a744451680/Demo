package top.isyl.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *  线程池配置
 * @Author huangyunlong
 * @Date 2019/3/8
 */
/**
 * @ComponentScan  生效包路径
 */
@Configuration
@EnableAsync
@ComponentScan("top.isyl.demo.controller")
public class AsyncTaskConfig {

    private static final Logger logger = LoggerFactory.getLogger(AsyncTaskConfig.class);

    @Bean
    public Executor asyncExecutor() {
        logger.info("【线程池配置】");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(200);
        //配置最大线程数
        executor.setMaxPoolSize(200);
        //配置队列大小
        executor.setQueueCapacity(100000);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("My-Thread-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;



    }

}
