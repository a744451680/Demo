package top.isyl.demo.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.itextpdf.text.PageSize;
import io.swagger.annotations.Api;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.isyl.demo.entity.CURDInfo;
import top.isyl.demo.entity.ReportInfo;
import top.isyl.demo.service.CURDService;
import top.isyl.demo.util.FileUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author huangyunlong
 * @Date 2019/3/4
 */
@RestController
@RequestMapping("/report")
@Api(value = "报表Conller",tags = "报表接口")
public class ReportController {


    Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private CURDService curdService;

    /**
     * 导出
     *
     * @param response
     * @return
     */
    @GetMapping("/exportTest")
    public Object exportTest(HttpServletResponse response) {
        log.info(">>>>Excel导出 ");
        TemplateExportParams params = new TemplateExportParams("exceltemplate/excel导出模板.xls", true);
        List<CURDInfo> infoList = curdService.selectInfoList();
        Map<String, Object> map = new HashMap<>(16);
        map.put("data", infoList);
        map.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        FileUtil.downLoadExcel("exPort.xls", response, workbook);
        log.info(">>>>Excel导出 成功");
        return null;
    }

    /**
     * 导入
     *
     * @return
     */
    @PostMapping("/importTest")
    public Object importTest(MultipartFile file) {
        log.info(">>>>Excel导入 ");

       /* String filePath = "C:\\Users\\Administrator\\Desktop\\eee.xls";
        //解析excel，
        List<CURDInfo> infoList = FileUtil.importExcel(filePath,2,1,CURDInfo.class);*/

        List<CURDInfo> infoList = FileUtil.importExcel(file, 2, 1, CURDInfo.class);

        log.info("导入：{}", infoList);
        return infoList;
    }

    /**
     * 导入 两行
     *
     * @param
     * @return
     */
    @PostMapping("/importTest2")
    public Object importTest2(MultipartFile file) throws FileNotFoundException {
        log.info(">>>>Excel导入 ");

//        方法一：绝对路径
//        String file = "C:\\Users\\Administrator\\Desktop\\importTest.xls";
//        方法二：接口传参（推荐）
//        List<ReportInfo> infoList = FileUtil.importExcel(file,1,2,ReportInfo.class);
//        方法三：相对路径
        File file4 = ResourceUtils.getFile("classpath:exceltemplate/importTest.xls");
        String path = file4.getAbsolutePath();
        List<ReportInfo> infoList = FileUtil.importExcel( path,1,2,ReportInfo.class);

        log.info("导入：{}", infoList);
        return infoList;
    }





    /**
     * 打印
     *
     * @param response
     * @return
     */
    @GetMapping("/printTest")
    public Object printTest(HttpServletResponse response) {
        log.info(">>>>pdf打印 ");

        List<CURDInfo> infoList = curdService.selectInfoList();
        Map<String, Object> map = new HashMap<>(16);
        map.put("data", infoList);
        map.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

//      纵向打印
        FileUtil.printPdf(map, "pdf打印模板.html", PageSize.A4, response);
//      横向打印
//        FileUtil.printPdf(map, "pdf打印模板.html", PageSize.A4.rotate(), response);
        log.info("打印成功");
        return infoList;
    }
}
