package top.isyl.demo.controller;


import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.isyl.demo.entity.AjaxResult;
import top.isyl.demo.entity.YlCardInfo;
import top.isyl.demo.service.IYlCardInfoService;

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
    @ApiOperation(value = "查询微信菜单")
    public AjaxResult getWxMenux() {

        log.info("查询微信菜单");
        YlCardInfo cardInfo;
        try{
            cardInfo = cardInfoService.getRandomCard();
        }catch (Exception e){
            return new AjaxResult().fail();
        }
        log.info("查询微信菜单 成功");
        return new AjaxResult().success().res(cardInfo);
    }

}
