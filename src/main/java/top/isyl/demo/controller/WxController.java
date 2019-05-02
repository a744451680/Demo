package top.isyl.demo.controller;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import top.isyl.demo.entity.TextMessage;
import top.isyl.demo.entity.YlCardInfo;
import top.isyl.demo.service.IYlCardInfoService;
import top.isyl.demo.service.WeatherService;
import top.isyl.demo.util.CheckUtil;
import top.isyl.demo.util.WxTextUtils;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/wx")
public class WxController {

    @Autowired
    WeatherService weatherService;
    @Autowired
    IYlCardInfoService cardInfoService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 微信入口
     *
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping("/wx.do")
    public void wxMain(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String method = req.getMethod();
        System.out.println(method);
//		HttpURLConnecti
        if ("GET".equals(method)) {
            wxGet(req, resp);
        } else if ("POST".equals(method)) {
            wxPost(req, resp);
        }
    }

    //微信get入口
    public void wxGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        logger.info("----------------wxGet--------------------");
        String signature = req.getParameter("signature");// 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String timestamp = req.getParameter("timestamp");// 时间戳
        String nonce = req.getParameter("nonce");// 随机数
        String echostr = req.getParameter("echostr");// 随机字符串

        //校验信息
        if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
            PrintWriter pw = resp.getWriter();
            pw.write(echostr);
        }
    }

    //微信post入口
    public void wxPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("----------------wxPost--------------------");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sysDate = df.format(new Date());

        try {
            Map e = WxTextUtils.xml2Map(req);
            String toUserName = (String) e.get("ToUserName");
            String fromUserName = (String) e.get("FromUserName");
            String msgType = (String) e.get("MsgType");
            String content = (String) e.get("Content");
            logger.info(" 接受到的消息：" + content);
            String resultXml = null;
            if ("text".equals(msgType)) {
                TextMessage event = new TextMessage();
                event.setToUserName(fromUserName);
                event.setFromUserName(toUserName);
                event.setMsgType("text");
                event.setCreateTime(String.valueOf((new Date()).getTime()));
                // 返回的信息
                String wxContent = "";
                if (content.startsWith("天气=")) {
                    //如果是【天气+】开头 证明是查询天气
                    String cityName = content.replace("天气=", "");
                    logger.info("查询天气 cityName={}", cityName);
                    if (cityName.endsWith("省") || cityName.endsWith("市")) {
                        cityName = cityName.substring(0, cityName.length() - 1);
                        logger.info("subString: cityName={}", cityName);
                    }
                    //查询城市码
                    String cityCode = weatherService.getCityCode(cityName);
                    logger.info("查询城市码结果：" + cityCode);
                    if (cityCode != null && !cityCode.trim().isEmpty()) {
                        //根据城市码查询天气
                        String weather = weatherService.getWeather(cityCode);
                        wxContent = weather;
                    } else {
                        content = "查询天气格式：天气=【城市名】";
                        wxContent = content;
                    }
                } else if (content.equals("菜单") || content.equals("帮助") || content.equals("help")) {
                    wxContent = this.getHelpMenu();
                } else if (content.equals("百度") || content.equals("百度一下") || content.equals("101")) {
                    wxContent = "<a href=\"http://www.baidu.com\">百度一下</a>";
                } else if (content.equals("双色球") || content.equals("ssq") || content.equals("102")) {
                    wxContent = "<a href=\"http://www.isyl.top/ssq\">双色球</a>";
                } else if (content.equals("天气") || content.equals("weather") || content.equals("103")) {
                    wxContent = "<a href=\"http://www.weather.com.cn/weather/101210101.shtml\">天气</a>";
                } else if (content.equals("身份证") || content.equals("随机身份证") || content.equals("104")) {
                    YlCardInfo card = cardInfoService.getRandomCard();
                    StringBuffer stringBuffer = new StringBuffer();
                    if (ObjectUtil.isNotNull(card)) {
                        stringBuffer.append("id:   \t").append(card.getId());
                        stringBuffer.append("\r\nname: \t").append(card.getName());
                        stringBuffer.append("\r\ncard: \t").append(card.getCard());
                        stringBuffer.append("\r\nphone:\t").append(card.getPhone());
                        stringBuffer.append("\r\nemail: \t").append(card.getMail());
                        stringBuffer.append("\r\npwd:  \t").append(card.getPwd());
                    }
                    wxContent = stringBuffer.toString();
                } else {
                    wxContent = "学话：" + content;
                }

                event.setContent(wxContent);
                resultXml = WxTextUtils.object2xml(event);

            } else if ("event".equals(msgType)) {
                String event1 = (String) e.get("Event");
                System.out.println("event:" + event1);
                if ("subscribe".equals(event1)) {
                    // 首次登陆
                    String firstText = WxTextUtils.firstText();
                    resultXml = WxTextUtils.initText(toUserName, fromUserName, firstText);
                } else if ("subscribe".equals(event1)) {
                    resultXml = "SUCCESS";
                }
            }

            pw.print(resultXml);
        } catch (Exception var17) {
            var17.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }

        }
    }

    /**
     * 获取帮助菜单
     */
    private String getHelpMenu() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("帮助菜单：\r\n");
        stringBuffer.append("101： <a href=\"http://www.baidu.com\">百度一下</a> \r\n");
        stringBuffer.append("102： <a href=\"http://www.isyl.top/ssq\">双色球</a> \r\n");
        stringBuffer.append("103： <a href=\"http://www.weather.com.cn/weather/101210101.shtml\">天气</a> \r\n");
        stringBuffer.append("104： <a href=\"http://www.isyl.top/wx/card-info/random\">随机身份证</a> ");
        return stringBuffer.toString();
    }
}
