package top.isyl.demo.service;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @Author huangyunlong
 * @Date 2019/3/8
 */
public interface ThreadService {

    void sayHello(Integer num, List<Integer> list, CountDownLatch countDownLatch);
}
