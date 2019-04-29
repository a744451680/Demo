package top.isyl.demo.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.isyl.demo.service.ITAccessTokenInfoService;

import javax.annotation.Resource;

/**
 * @Author huangyunlong
 * @Date 2019/3/1
 */

@Component
public class TaskController {
    @Resource
    ITAccessTokenInfoService accessTokenService;


//    @Scheduled(cron = "0 0 0 * * ?")
//    public void taskPrint() {
//        System.out.println("定时任务：每天0点执行。");
//    }

//    @Scheduled(cron = "*/2 * * * * ?")
//    public void taskPrint2(){
//        System.out.println(Thread.currentThread()+"定时任务：每秒执行一次。");
//    }

    @Scheduled(cron = "0 0 */1 * * ?")
    public void getAccessToken() {

        System.out.println("定时任务：获取AccessToken  start！");
        accessTokenService.updateAccessToken();
        System.out.println("定时任务：获取AccessToken  end！");
    }



}
