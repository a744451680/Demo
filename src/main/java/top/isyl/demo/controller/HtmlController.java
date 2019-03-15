package top.isyl.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.isyl.demo.annotation.Count;

/**
 * @Author huangyunlong
 * @Date 2019/2/23
 */
@Controller
@Api(value="/test1", tags="测试页面")
@RequestMapping("/")
public class HtmlController {

    @GetMapping("/index")
    @Count("page_view")
    @ApiOperation(value="测试index页面", notes = "测试index页面xxx")
    public String toIndex() {
        return "index";
    }

    @GetMapping("/ssq")
    @ApiOperation(value="测试ssq面")
    public String toSsq() {
        return "ssq";
    }

    @GetMapping("/")
    @ApiOperation(value="测试欢迎页面")
    public String index() {
        return this.toIndex();
    }

    @GetMapping("/line")
    @ApiOperation(value="测试line页面")
    public String line() {
        return "line";
    }
}