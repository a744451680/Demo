package top.isyl.demo.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.isyl.demo.entity.AjaxResult;
import top.isyl.demo.entity.TTempMessage;
import top.isyl.demo.service.ITTempMessageService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hyl
 * @since 2019-05-27
 */
@RestController
@RequestMapping("/t-temp-message")
@Api(value="/临时短息controller", tags="临时短息测试页面")
@Slf4j
public class TTempMessageController {

    @Autowired
    private ITTempMessageService tempMessageService;


    /**
     * 获取手机号列表
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/phoneIds")
    @ApiOperation(value = "获取手机号列表",httpMethod = "GET")
    public AjaxResult getPhoneIds() {
        log.info("获取列表");
        try {
            List<String> phoneIds = tempMessageService.getPhoneIds();
            log.info("获取手机号列表 信息出参：{}",phoneIds);
            return new AjaxResult().success().res(phoneIds);
        }catch (Exception e){
            log.info("获取手机号列表 信息失败 {}",e);
            return new AjaxResult().fail().res(e.getMessage());
        }
    }



    /**
     * 查询信息列表
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/message")
    @ApiOperation(value = "查询信息列表",httpMethod = "GET")
    public Object getMessage(@ApiParam(name = "phoneId", value = "手机号", required = true) @RequestParam String phoneId) throws Exception {
        log.info("查询信息列表入参：phoneId:{}", phoneId);
        List<TTempMessage> messages = tempMessageService.getMessage(phoneId);
        return messages;
    }


}
