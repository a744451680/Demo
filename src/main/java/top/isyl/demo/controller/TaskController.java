package top.isyl.demo.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author huangyunlong
 * @Date 2019/3/1
 */

@Component
public class TaskController {


    @Scheduled(cron = "0 0 */1 * * ?")
    public void taskPrint() {
        System.out.println("定时任务：每小时执行一次。");
    }

//    @Scheduled(cron = "*/2 * * * * ?")
//    public void taskPrint2(){
//        System.out.println(Thread.currentThread()+"定时任务：每秒执行一次。");
//    }

}
