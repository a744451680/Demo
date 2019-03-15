package top.isyl.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import top.isyl.demo.service.ThreadService;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author huangyunlong
 * @Date 2019/3/8
 */
@Service
public class ThreadServiceImpl implements ThreadService {

    private static final Logger logger = LoggerFactory.getLogger(ThreadServiceImpl.class);


    /**
     * 多线程测试
     *
     * @param nul
     * @param countDownLatch
     * @Async ：在配置类中定义的asyncExecutor线程池，
     */
    @Override
    @Async("asyncExecutor")
    public void sayHello(Integer nul, List<Integer> list, CountDownLatch countDownLatch) {
//        logger.info("多线程测试开始--------start");
        try {
            Integer remove = list.remove(0);
            Thread.sleep(5000 + Integer.valueOf(remove));
            logger.info(remove + "////");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
            logger.info(countDownLatch.getCount() + "::::count");
        }
//        logger.info("多线程测试结束--------end");
    }
}
