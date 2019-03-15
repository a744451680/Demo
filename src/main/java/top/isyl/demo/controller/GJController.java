package top.isyl.demo.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import top.isyl.demo.entity.CURDInfo;
import top.isyl.demo.util.HttpRequestUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author huangyunlong
 * @Date 2019/2/26
 */
@RestController
//@Controller
@RequestMapping("/gj")
@Api(value = "公交接口Controller", tags = "公交接口")
public class GJController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取线路名称
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/lines")
    @ApiOperation(value = "获取线路名称")
    public List getLines() throws Exception {
        log.info("获取线路名称");
        String s = HttpRequestUtil.get("http://www.bjbus.com/home/index.php");
        Document document = Jsoup.parse(s);
        Element Lines = document.getElementById("selBLine");
        Elements as = Lines.select("a");

        List<String> lineList = new ArrayList<>();
        as.forEach(x -> {
            lineList.add(x.ownText());
        });
        log.info("获取线路名称出参：{}", lineList.size());
        return lineList;
    }

    /**
     * 获取线路方向
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/lineDir")
    @ApiOperation(value = "获取线路方向",
            notes = "使用 lineNo=1 进行测试。")
    public List getLineDir(@ApiParam(name = "lineNo", value = "线路编号", required = true) @RequestParam String lineNo) throws Exception {
        log.info("获取线路方向入参：lineNo：{}", lineNo);
        String s = HttpRequestUtil.get("http://www.bjbus.com/home/ajax_rtbus_data.php?act=getLineDir&selBLine=" + lineNo);
        Document document = Jsoup.parse(s);

        //所有A标签
        Elements allElements = document.select("a");
        List<CURDInfo> lineDirList = new ArrayList<>();

        allElements.forEach(x -> {
            //方向名称
            String dirName = x.ownText();
            if (StringUtils.isNotBlank(dirName)) {
                CURDInfo direction = new CURDInfo();
                Map<String, String> dataMap = x.dataset();
                //方向id
                direction.setId(dataMap.get("uuid"));
                direction.setName(dirName);
                lineDirList.add(direction);
            }
        });
        log.info("获取线路方向出参：{}", lineDirList.size());
        return lineDirList;
    }


    /**
     * 获取站点信息
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/dirStation")
    @ApiOperation(value = "获取站点信息")
    public List getDirStation(@ApiParam(name = "lineNo", value = "线路编号", required = true) @RequestParam String lineNo
            , @ApiParam(name = "dirId", value = "乘车方向编号", required = true) @RequestParam String dirId) throws Exception {
        log.info("获取站点信息入参：lineNo:{} dirId:{}", lineNo, dirId);
        String s = HttpRequestUtil.get("http://www.bjbus.com/home/ajax_rtbus_data.php?act=getDirStation&selBLine=" + lineNo + "&selBDir=" + dirId);
        Document document = Jsoup.parse(s);

        //所有A标签
        Elements allElements = document.select("a");
        List<CURDInfo> dirStationList = new ArrayList<>();

        allElements.forEach(x -> {
            //站点名称
            String dirName = x.ownText();
            if (StringUtils.isNotBlank(dirName)) {
                CURDInfo direction = new CURDInfo();
                Map<String, String> dataMap = x.dataset();
                //站点id
                direction.setId(dataMap.get("seq"));
                direction.setName(dirName);
                dirStationList.add(direction);
            }
        });
        log.info("获取站点信息出参：{}", dirStationList.size());
        return dirStationList;
    }


    /**
     * 获取公交实时信息
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/busTime")
    @ApiOperation(value = "获取公交实时信息")
    public Object getBusTime(@ApiParam(name = "lineNo", value = "线路编号", required = true) @RequestParam String lineNo
            , @ApiParam(name = "dirId", value = "乘车方向编号", required = true) @RequestParam String dirId
            , @ApiParam(name = "sdsId", value = "站点信息编号", required = true) @RequestParam String sdsId) throws Exception {
        log.info("获取公交实时信息入参：lineNo:{} dirId:{}  sdsId:{}", lineNo, dirId, sdsId);
        String s = HttpRequestUtil.get("http://www.bjbus.com/home/ajax_rtbus_data.php?act=busTime&selBLine=" + lineNo + "&selBDir=" + dirId + "&selBStop=" + sdsId);
        Map mapTypes = JSON.parseObject(s);
        return mapTypes;
    }


}
