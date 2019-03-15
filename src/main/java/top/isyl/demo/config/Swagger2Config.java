package top.isyl.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author huangyunlong
 * @Date 2019/2/28
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    private static final Logger logger = LoggerFactory.getLogger(Swagger2Config.class);

    @Bean
    public Docket createRestApi() {
        logger.info("【swagger2配置】");
        return new Docket(DocumentationType.SWAGGER_2)
//                .host("127.0.0.1:9090")
                .apiInfo(apiInfo())
                .select() // 选择那些路径和api会生成document
                // controller 包名
                .apis(RequestHandlerSelectors.basePackage("top.isyl.demo.controller")) //api接口包扫描路径
                .paths(PathSelectors.any()) // 对所有路径进行监控
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("标题：demo利用swagger构建api文档")//设置文档的标题
                .description("描述：更多内容请关注：http://www.isyl.top")//设置文档的描述
                //服务条款网址
//                .termsOfServiceUrl("http://isyl.top/xxxxx")
                .version("版本：1.0")
                .build();
    }

}