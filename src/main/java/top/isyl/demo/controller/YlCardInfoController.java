package top.isyl.demo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.isyl.demo.entity.AjaxResult;
import top.isyl.demo.entity.YlCardInfo;
import top.isyl.demo.service.IYlCardInfoService;

import java.util.List;

/**
 * <p>
 * 身份信息表 前端控制器
 * </p>
 *
 * @author hyl
 * @since 2019-05-01
 */
@RestController
@RequestMapping("/wx/card-info")
@Slf4j
public class YlCardInfoController {

    @Autowired
    IYlCardInfoService cardInfoService;

    /**
     * 获取随机身份证信息
     */
    @GetMapping("/random")
    @ApiOperation(value = "获取随机身份证信息")
    public AjaxResult getWxMenux() {

        log.info("获取随机身份证信息");
        YlCardInfo cardInfo;
        try {
            cardInfo = cardInfoService.getRandomCard();
        } catch (Exception e) {
            return new AjaxResult().fail();
        }
        log.info("获取随机身份证信息 成功");
        return new AjaxResult().success().res(cardInfo);
    }

    /**
     * 获取身份信息 年份 列表
     */
    @GetMapping("/cardYears")
    @ApiOperation(value = "获取身份信息 年份 列表")
    public AjaxResult getCardYears() {

        log.info("获取身份信息 年份 列表");
        List<String> years;
        try {
            years = cardInfoService.getCaedYears();
        } catch (Exception e) {
            return new AjaxResult().fail();
        }
        log.info("获取身份信息 年份 列表 成功");
        return new AjaxResult().success().res(years);
    }


    /**
     * 分页查询身份信息
     */
    @GetMapping("/getCardPageInfo")
    @ApiOperation(value = "获取身份信息 年份 列表")
    public AjaxResult getCardPageInfo(@ApiParam(name = "pageNum", value = "页码") @RequestParam(defaultValue = "1") Integer pageNum
            , @ApiParam(name = "pageSize", value = "每页显示数量") @RequestParam(defaultValue = "5") Integer pageSize
            , @ApiParam(name = "name", value = "姓名") @RequestParam(required = false) String name
            , @ApiParam(name = "startDate", value = "起始日期") @RequestParam(required = false) String startDate
            , @ApiParam(name = "endDate", value = "结束日期") @RequestParam(required = false) String endDate) {
        log.info("获取身份信息 年份 列表 name:{} ,startDate:{} ,endDate:{} ,pageNum{} ,pageSize:{}", name, startDate, endDate, pageNum, pageSize);
        IPage<YlCardInfo> pageList;
        try {
            pageList = cardInfoService.cardPageList(name, startDate, endDate, pageNum, pageSize);
        } catch (Exception e) {
            return new AjaxResult().fail();
        }
        log.info("获取身份信息 年份 列表 成功");
        return new AjaxResult().success().res(pageList);
    }

}
