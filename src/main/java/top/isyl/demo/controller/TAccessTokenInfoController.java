package top.isyl.demo.controller;


import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.isyl.demo.entity.AjaxResult;
import top.isyl.demo.service.ITAccessTokenInfoService;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hyl
 * @since 2019-04-22
 */
@RestController
@RequestMapping("/t-access-token-info")
@Slf4j
public class TAccessTokenInfoController {

    @Resource
    ITAccessTokenInfoService accessTokenInfoService;

    /**
     * 手动更新AccessToken
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/updateAccessToken")
    @ApiOperation(value = "手动获取AccessToken")
    public AjaxResult getLines(){
        log.info("手动获取AccessToken");
        try{
            accessTokenInfoService.updateAccessToken();
        }catch (Exception e){
            return new AjaxResult().fail();
        }
        log.info("手动获取AccessToke成功");
        return new AjaxResult().success();
    }
}
