package top.isyl.demo.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import top.isyl.demo.entity.TTempMessage;
import top.isyl.demo.entity.TextMessage;
import top.isyl.demo.entity.YlCardInfo;
import top.isyl.demo.service.ITTempMessageService;
import top.isyl.demo.service.IYlCardInfoService;
import top.isyl.demo.service.WeatherService;
import top.isyl.demo.util.CheckUtil;
import top.isyl.demo.util.WxTextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/wx")
@Slf4j
public class WxController {

    @Autowired
    WeatherService weatherService;
    @Autowired
    IYlCardInfoService cardInfoService;
    @Autowired
    ITTempMessageService tempMessageService;


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
        log.info("----------------wxGet--------------------");
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
        log.info("----------------wxPost--------------------");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter pw = resp.getWriter();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sysDate = df.format(new Date());

        try {
            Map e = WxTextUtils.xml2Map(req);
            String toUserName = (String) e.get("ToUserName");
            String fromUserName = (String) e.get("FromUserName");  //openid
            String msgType = (String) e.get("MsgType");
            String content = ((String) e.get("Content")).trim();
            log.info(" 接受到的消息：" + content);
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
                    wxContent = this.getWeather(content);
                } else if (content.equals("菜单") || content.equals("帮助") || content.equals("help")) {
                    wxContent = this.getHelpMenu();
                } else if (content.equals("百度") || content.equals("百度一下") || content.equals("101")) {
                    wxContent = "<a href=\"http://www.baidu.com\">百度一下</a>";
                } else if (content.equals("双色球") || content.equals("ssq") || content.equals("102")) {
                    wxContent = "<a href=\"http://www.isyl.top/ssq\">双色球</a>";
                } else if (content.equals("天气") || content.equals("weather") || content.equals("103")) {
                    wxContent = "<a href=\"http://www.weather.com.cn/weather/101210101.shtml\">☞天气☜</a>";
                } else if (content.equals("帮百度") || content.equals("需要我帮你百度么") || content.equals("104")) {
                    wxContent = "<a href=\"http://t.cn/EyP3WdS\">☞需要我帮你百度么？☜</a>";
                } else if (content.equals("手机号") || content.equals("临时号") || content.equals("105")) {
                    wxContent = this.getPhoneIds();
                } else if (content.startsWith("查短信") || content.equals("106")) {
                    wxContent = this.getMessage(content);
                } else if (content.equals("身份证")) {
                    wxContent = this.getRandomCardContent();
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
     * 查短信
     *
     * @return
     */
    private String getPhoneIds() {
        String wxContent = "可用接受短信临时手机：";
        List<String> phoneIds = tempMessageService.getPhoneIds();
        if (CollectionUtil.isNotEmpty(phoneIds)) {
            for (String phoneId : phoneIds) {
                wxContent = wxContent + "\r\n" + phoneId;
            }
        } else {
            wxContent = "暂无可用手机号，请稍后再试";
        }
        return wxContent;
    }

    /**
     * 查短信
     *
     * @param content
     * @return
     */
    private String getMessage(String content) {
        String wxContent = "";
        if (content.length() < 4) {
            wxContent = "【查短信】功能使用方式：\r\n" +
                    "发送文字[查短信+临时手机号] \r\n" +
                    "例：   查短信16739465446 ";
        } else {
            log.info("wxController.getMessage(content)    :" + content);
            String phoneId = content.substring(3);
            wxContent = phoneId + ":最近10条短信内容：";
            List<TTempMessage> msgList = tempMessageService.getMessage(phoneId);
            if (CollectionUtil.isNotEmpty(msgList)) {
                for (TTempMessage tempMessage : msgList) {
                    wxContent = wxContent + "\r\n \r\n[" + tempMessage.getPhoneId() + "][" + tempMessage.getMsgUrl() + "][" + tempMessage.getCreateTime() + "]";
                }
            } else {
                wxContent = phoneId + "暂未查到短信记录，请稍后再试";
            }
        }

        return wxContent;
    }

    /**
     * 查天气接口暂时不好用了以后维护
     *
     * @param content
     * @return
     */
    private String getWeather(String content) {
        String wxContent;
        //如果是【天气+】开头 证明是查询天气
        String cityName = content.replace("天气=", "");
        log.info("查询天气 cityName={}", cityName);
        if (cityName.endsWith("省") || cityName.endsWith("市")) {
            cityName = cityName.substring(0, cityName.length() - 1);
            log.info("subString: cityName={}", cityName);
        }
        //查询城市码
        String cityCode = weatherService.getCityCode(cityName);
        log.info("查询城市码结果：" + cityCode);
        if (cityCode != null && !cityCode.trim().isEmpty()) {
            //根据城市码查询天气
            String weather = weatherService.getWeather(cityCode);
            wxContent = weather;
        } else {
            content = "查询天气格式：天气=【城市名】";
            wxContent = content;
        }
        return wxContent;
    }

    /**
     * 获取随机身份信息str
     *
     * @return
     */
    private String getRandomCardContent() {
        String wxContent;
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
        return wxContent;
    }

    /**
     * 获取帮助菜单
     */
    private String getHelpMenu() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("帮助菜单：\r\n");
        stringBuffer.append("101： <a href=\"http://www.baidu.com\">☞百度一下☜</a> \r\n");
        stringBuffer.append("102： <a href=\"http://www.isyl.top/ssq\">☞双色球☜</a> \r\n");
        stringBuffer.append("103： <a href=\"http://www.weather.com.cn/weather/101210101.shtml\">☞天气☜</a> \r\n");
        stringBuffer.append("104： <a href=\"http://t.cn/EyP3WdS\">☞要我帮你百度一下么？☜</a> \r\n");
        stringBuffer.append("105： <a >☞临时号☜</a>  \r\n");
        stringBuffer.append("106： <a >☞查短信☜</a> ");
//        stringBuffer.append("104： <a href=\"http://www.isyl.top/wx/card-info/random\">☞随机身份证☜</a> ");
        return stringBuffer.toString();
    }
}
