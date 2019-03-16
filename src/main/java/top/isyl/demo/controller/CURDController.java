package top.isyl.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import top.isyl.demo.service.CURDService;
import top.isyl.demo.entity.AjaxResult;
import top.isyl.demo.entity.CURDInfo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author huangyunlong
 * @Date 2019/2/28
 */
@Api(value = "增删改查Controller", tags = "增删改查")
@RestController
@RequestMapping("/curd")
public class CURDController {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource
    private CURDService curdService;

    /**
     * 获取列表
     *
     * @return
     */
    @ApiOperation(value = "获取列表")
    @GetMapping("/list")
    public AjaxResult selectInfoList() {
        log.info("获取列表");
        List<CURDInfo> curdInfos = curdService.selectInfoList();
        return new AjaxResult().res(curdInfos);
    }


    /**
     * 获取列表
     *
     * @return
     */
    @ApiOperation(value = "获取列表")
    @GetMapping("/list")
    public AjaxResult selectInfoList(@ApiParam(name = "pageNum", value = "页码") @RequestParam(defaultValue = "1") Integer pageNum
            , @ApiParam(name = "pageSize", value = "每页显示数量") @RequestParam(defaultValue = "20") Integer pageSize) {
        log.info("获取列表  pageNum:{}  pageSize:{]",pageNum,pageSize);
        IPage<CURDInfo> curdInfos = curdService.selectInfoPage(pageNum,pageSize);
        return new AjaxResult().res(curdInfos);
    }


    /**
     * 查询总记录数
     *
     * @return
     */
    @ApiOperation(value = "查询总记录数")
    @GetMapping("/getCount")
    public AjaxResult selectCount() {
        log.info("查询总记录数");
        String count = curdService.selectCount();
        return new AjaxResult().res(count);
    }


    /**
     * 根据id查询对象
     *
     * @return
     */
    @ApiOperation(value = "根据id查询对象")
    @GetMapping("/getById")
    public AjaxResult getById(@ApiParam(name = "id", value = "编号", required = true) @RequestParam String id) {
        log.info("根据id查询对象 id:{}", id);
        CURDInfo curdInfo = curdService.getInfoById(id);
        return new AjaxResult().res(curdInfo);
    }


    /**
     * 添加
     *
     * @return
     */
    @ApiOperation(value = "添加")
    @GetMapping("/setInfo")
    public AjaxResult setInfo(CURDInfo curdInfo) {
        log.info("根据id查询对象 curdInfo:{}", curdInfo);
        curdService.saveOrUpdate(curdInfo);
        return new AjaxResult().success();
    }


    /**
     * 删除
     *
     * @return
     */
    @ApiOperation(value = "删除")
    @DeleteMapping("/delInfo")
    public AjaxResult delInfo(String id) {
        log.info("根据id查询对象 id:{}", id);
        try {
            boolean b = curdService.remove(new QueryWrapper<CURDInfo>().eq("id", id));
            return  new AjaxResult().res(new AjaxResult<>().res(b));
        }catch (Exception e){
            return  new AjaxResult().fail();
        }
    }


}
