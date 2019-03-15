package top.isyl.demo.config;

import org.springframework.core.Ordered;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author huangyunlong
 * @Date 2019/2/25
 */
@EnableScheduling
public class MvcConfig extends WebMvcConfigurerAdapter  {
   /* @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("forward:/index.html");

        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        super.addViewControllers(registry);

    }*/
}