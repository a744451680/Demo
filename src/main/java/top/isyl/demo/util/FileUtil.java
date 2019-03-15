package top.isyl.demo.util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * @author adminintrator
 * @Description Excel工具类
 * @date
 */
public class FileUtil {
    private static final String FONT = "font/simhei.ttf";

    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, boolean isCreateHeader, HttpServletResponse response) {
        ExportParams exportParams = new ExportParams(title, sheetName);
        exportParams.setCreateHeadRows(isCreateHeader);
        defaultExport(list, pojoClass, fileName, response, exportParams);

    }

    public static void exportExcel(List<?> list, String title, String sheetName, Class<?> pojoClass, String fileName, HttpServletResponse response) {
        defaultExport(list, pojoClass, fileName, response, new ExportParams(title, sheetName));
    }

    public static void exportExcel(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        defaultExport(list, fileName, response);
    }

    private static void defaultExport(List<?> list, Class<?> pojoClass, String fileName, HttpServletResponse response, ExportParams exportParams) {
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        if (workbook != null) {
            downLoadExcel(fileName, response, workbook);
        }
    }

    public static void downLoadExcel(String fileName, HttpServletResponse response, Workbook workbook) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void defaultExport(List<Map<String, Object>> list, String fileName, HttpServletResponse response) {
        Workbook workbook = ExcelExportUtil.exportExcel(list, ExcelType.HSSF);
        if (workbook != null) {
            downLoadExcel(fileName, response, workbook);
        }
    }

    public static <T> List<T> importExcel(String filePath,Integer titleRows,Integer headerRows, Class<T> pojoClass){
        if (StringUtils.isBlank(filePath)){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(new File(filePath), pojoClass, params);
        }catch (NoSuchElementException e){
            throw new NoSuchElementException("模板不能为空");
        } catch (Exception e) {
            e.printStackTrace();
            throw new NoSuchElementException(e.getMessage());
        }
        return list;
    }
    public static <T> List<T> importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass){
        if (file == null){
            return null;
        }
        ImportParams params = new ImportParams();
        params.setTitleRows(titleRows);
        params.setHeadRows(headerRows);
        List<T> list = null;
        try {
            list = ExcelImportUtil.importExcel(file.getInputStream(), pojoClass, params);
        }catch (NoSuchElementException e){
            throw new RuntimeException("excel文件不能为空");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return list;
    }

    /**
     * pdf 字节流响应
     *
     * @param response
     * @param result   pdf字节输出流 字符串
     * @throws IOException
     */
    public static void pdfResponse(HttpServletResponse response, String result) throws IOException {
        //如果跨域需设置解码
        result = URLDecoder.decode(result, "ISO-8859-1");
        ByteArrayInputStream inStream = new ByteArrayInputStream(result.getBytes("ISO-8859-1"));
        // 循环取出流中的数据
        byte[] b = new byte[2048];
        int len;
        try {
            while ((len = inStream.read(b)) > 0) {
                response.getOutputStream().write(b, 0, len);
            }
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成pdf
     *
     * @param content freemarker 渲染后输出的文本
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static String createPdf(String content,Rectangle rectPageSize) throws IOException, DocumentException {
        //打印大小
        if(rectPageSize == null) {
            rectPageSize = new Rectangle(PageSize.A4);
        }
        // step 1
        Document document = new Document(rectPageSize);
        // step 2
        //构建字节输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, baos);
        // step 3
        document.open();
        // step 4
        XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontImp.register(FONT);
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(content.getBytes()), null, Charset.forName("UTF-8"), fontImp);
        // step 5
        document.close();
        baos.close();
        //转字符串设置编码
        String result = new String(baos.toByteArray(), "ISO-8859-1");
        return URLEncoder.encode(result, "ISO-8859-1");//如果跨域需设置编码
    }

    /**
     * 生成pdf
     *
     * @param content freemarker 渲染后输出的文本
     * @param dest    生成pdf所在文件位置
     * @throws IOException
     * @throws DocumentException
     */
    public void createPdf(String content, String dest) throws IOException, DocumentException {
        Rectangle rectPageSize = new Rectangle(PageSize.A4);
        Document document = new Document(rectPageSize);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        XMLWorkerFontProvider fontImp = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontImp.register(FONT);
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(content.getBytes()), null, Charset.forName("UTF-8"), fontImp);
        document.close();
    }

    /**
     * freemarker 渲染 html
     *
     * @param data    数据 Map<String,Object>
     * @param htmlTmp 模板名称
     * @return
     */
    public static String freeMarkerRender(Map<String, Object> data, String htmlTmp) {
        Configuration freemarkerCfg = new Configuration();
        //freemarker的模板目录 "/freemakertemplate/"
        freemarkerCfg.setClassForTemplateLoading(FileUtil.class, "/freemakertemplate");
        Writer out = new StringWriter();
        try {
            // 获取模板,并设置编码方式
            Template template = freemarkerCfg.getTemplate(htmlTmp);
            template.setEncoding("UTF-8");
            // 合并数据模型与模板
            //将合并后的数据和模板写入到流中，这里使用的字符流
            template.process(data, out);
            out.flush();
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * pdf 打印
     *
     * @param data
     * @param freemarkertemplate
     * @param rectPageSize  纸张大小 （横向:   rectPageSize.rotate() ）
     * @param response
     */
    public static void printPdf(Map<String, Object> data, String freemarkertemplate,Rectangle rectPageSize, HttpServletResponse response) {
        //freemarker 模板渲染
        String content = freeMarkerRender(data, freemarkertemplate);
        try {
            String result = createPdf(content,rectPageSize);
            pdfResponse(response, result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
