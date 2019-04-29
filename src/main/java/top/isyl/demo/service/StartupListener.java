package top.isyl.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 服务器启动执行 监听器
 * @author Ylong
 *
 */
@Service
@Slf4j
public class StartupListener implements ApplicationListener<ContextRefreshedEvent> {


    @Resource
    ITAccessTokenInfoService accessTokenService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent evt) {
        if (evt.getApplicationContext().getParent() == null) {
            log.info("-------------------------------服务器启动执行-----------------------------------------");
            log.info("-------------------------------更新AccessToken----------------------------------------");
            accessTokenService.updateAccessToken();
            log.info("-------------------------------更新AccessToken----------------------------------------");
            log.info("-------------------------------服务器启动执行-----------------------------------------");

        }

    }
}