/*
package top.isyl.demo.controller;


import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.isyl.demo.entity.AjaxResult;
import top.isyl.demo.entity.TWxMenu;
import top.isyl.demo.service.ITWxMenuService;
import top.isyl.demo.service.impl.TWxMenuServiceImpl;

import javax.annotation.Resource;

*/
/**
 * <p>
 * 微信菜单表 前端控制器
 * </p>
 *
 * @author hyl
 * @since 2019-04-22
 *//*

@RestController
@RequestMapping("/wx/t-wx-menu")
@Slf4j
public class TWxMenuController {

    @Resource
    ITWxMenuService wxMenuService;

    */
/**
     * 创建微信菜单(需要认证号)
     *//*

    @GetMapping("/createWxMenux")
    @ApiOperation(value = "创建微信菜单")
    public AjaxResult createWxMenux() {

        log.info("手动获取AccessToken");
        try{
            wxMenuService.createWxMenux();
        }catch (Exception e){
            return new AjaxResult().fail();
        }
        log.info("手动获取AccessToke成功");
        return new AjaxResult().success();
    }

    */
/**
     * 查询微信菜单(需要认证号)
     *//*

    @GetMapping("/getWxMenux")
    @ApiOperation(value = "查询微信菜单")
    public AjaxResult getWxMenux() {

        log.info("查询微信菜单");
        String menuJson;
        try{
            menuJson = wxMenuService.getWxMenux();
        }catch (Exception e){
            return new AjaxResult().fail();
        }
        log.info("查询微信菜单 成功");
        return new AjaxResult().success().res(menuJson);
    }

    */
/**
     * 删除微信菜单(需要认证号)
     *//*

    @GetMapping("/delectWxMenu")
    @ApiOperation(value = "删除微信菜单")
    public AjaxResult delectWxMenu() {

        log.info("删除微信菜单");
        try{
            wxMenuService.delectWxMenu();
        }catch (Exception e){
            return new AjaxResult().fail();
        }
        log.info("删除微信菜单 成功");
        return new AjaxResult().success();
    }
}
*/
