package top.isyl.demo.util;

import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import top.isyl.demo.entity.TextMessage;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WxTextUtils {

    //文本消息类型
    public static final String MESSAGE_TEXT = "text";

    //图片消息类型
    public static final String MESSAGE_IMAGE = "image";

    //语音消息类型
    public static final String MESSAGE_VOICE = "voice";

    //视频消息类型
    public static final String MESSAGE_VIDEO = "video";

    //小视频消息类型
    public static final String MESSAGE_SHORTVIDEO = "shortvideo";

    //链接消息类型
    public static final String MESSAGE_LINK = "link";

    //语音消息类型
    public static final String MESSAGE_voice = "voice";

    //视频事件类型
    public static final String MESSAGE_video = "video";


    //事件消息类型
    public static final String MESSAGE_EVENT = "event";

    //关注事件类型
    public static final String EVENT_SUBSCRIBE = "subscribe";

    //位置事件类型
    public static final String EVENT_LOCATION = "LOCATION";

    //点击菜单拉取菜单事件类型
    public static final String EVENT_CLICK = "CLICK";

    //点击菜单跳转链接事件类型
    public static final String EVENT_VIEW = "VIEW";


    /**
     * xml转map
     *
     * @param req
     * @return
     * @throws Exception
     */
    public static Map<String, String> xml2Map(HttpServletRequest req) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        InputStream inputStream = req.getInputStream();
        // 获取文档  
        Document document = reader.read(inputStream);
        // 获取根目录,并保存在list中  
        Element root = document.getRootElement();
        @SuppressWarnings("unchecked")
        List<Element> elements = root.elements();
        // 遍历所有目录并保持在集合中  
        for (Element element : elements) {
            map.put(element.getName(), element.getText());
        }
        inputStream.close();
        return map;
    }

    /**
     * TextMessage转XML
     *
     * @param textMessage
     * @return
     */
    public static String object2xml(TextMessage textMessage) {
        XStream stream = new XStream();
        // XStream对象将bean对象转成xml格式，默认根节为包名，需要 将xml的根节点替换成xml，否则微信后台无法解析  
        stream.alias("xml", textMessage.getClass());
        return stream.toXML(textMessage);
    }


    /**
     * 初始化主菜单
     *
     * @param toUserName
     * @param fromUserName
     * @param content
     * @return
     */

    public static String initText(String toUserName, String fromUserName, String content) {
        TextMessage text = new TextMessage();
        text.setFromUserName(toUserName);
        text.setToUserName(fromUserName);
        text.setMsgType(MESSAGE_TEXT);
        text.setCreateTime(System.currentTimeMillis() + "");
        text.setContent(content);
        return object2xml(text);
    }

    /**
     * 第一次关注
     *
     * @return
     */
    public static String firstText() {
        StringBuffer sb = new StringBuffer();
        sb.append("傻宝宝，这是你第一次来到这里 ~\r\n");
        sb.append("爱你~\r\n");
        sb.append("{\\__/}\r\n");
        sb.append("( ? - ?)\r\n");
        sb.append("/つ  我你要不要?\r\n");
        sb.append("\r\n");
        sb.append("当然你可以试试 输入[帮助]");
        return sb.toString();
    }
}
