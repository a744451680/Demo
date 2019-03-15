package top.isyl.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.isyl.demo.service.ThreadService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * 多线程
 *
 * @Author huangyunlong
 * @Date 2019/3/8
 */
@RestController
@RequestMapping("/thread")
@Api(value = "多线程Conller", tags = "多线程")
public class ThreadController {

    private static final Logger logger = LoggerFactory.getLogger(ThreadController.class);

    @Autowired
    private ThreadService threadService;


    /**
     * 100W条数据 每次发送200 每分钟最多1W条
     * @return
     */
    @GetMapping("/test")
    @ApiOperation(value = "测试")
//    @Scheduled(cron = "0 */1 * * * ?") // 定时测试时打开
    public String test() {
        //请求总数
        int clientTotal = 10000;
        //同时并发执行的线程数
        int threadTotal = 200;
        //倒计时计数器
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        //计数信号量
        Semaphore semaphore = new Semaphore(threadTotal);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < clientTotal; i++) {
            list.add(i);
        }
        for (int i = 0; i < clientTotal; i++) {
            try {
                //取许可
                semaphore.acquire();
                //执行任务
                threadService.sayHello(i, list, countDownLatch);
                //还许可
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            System.out.println("---------" + countDownLatch.getCount());
            countDownLatch.await();
            System.out.println("---------" + countDownLatch.getCount());
        } catch (InterruptedException e) {
            System.out.println("+++++++++++++++" + countDownLatch.getCount());
            e.printStackTrace();
        }
        return "SUCCESS";
    }

}
