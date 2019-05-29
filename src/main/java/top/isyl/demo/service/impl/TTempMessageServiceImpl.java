package top.isyl.demo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.transaction.annotation.Transactional;
import top.isyl.demo.entity.TTempMessage;
import top.isyl.demo.mapper.TTempMessageMapper;
import top.isyl.demo.service.ITTempMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.isyl.demo.util.DateUtil;
import top.isyl.demo.util.HttpRequestUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hyl
 * @since 2019-05-27
 */
@Service
@Slf4j
public class TTempMessageServiceImpl extends ServiceImpl<TTempMessageMapper, TTempMessage> implements ITTempMessageService {


    /**
     * 获取手机号列表
     *
     * @return
     */
    @Override
    public List<String> getPhoneIds() {

        List<TTempMessage> list = this.list();

        if (CollectionUtils.isEmpty(list)) {
            this.saveTempMessageInfos();
            list = this.list();
        }

        List<String> phoneIds = new ArrayList<>();
        list.forEach(x -> {
            phoneIds.add(x.getPhoneId());
        });
        return phoneIds;
    }

    /**
     * 保存临时短信info
     *
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveTempMessageInfos() {
        this.remove(new QueryWrapper<>());
        List<TTempMessage> netList = this.getNetList();
        this.saveBatch(netList);
    }

    /**
     * 查询信息列表
     *
     * @param phoneId
     * @return
     */
    @Override
    public List<TTempMessage> getMessage(String phoneId) {
        List<TTempMessage> netMessageList = null;
        phoneId = phoneId.trim();
        if (phoneId.startsWith("+86")) {
            phoneId = phoneId.substring(3);
        }

        TTempMessage message = this.getById(phoneId);

        if (ObjectUtil.isNotNull(message)) {
            netMessageList = this.getNetMessageList(message.getMsgUrl());

        }
        return netMessageList;
    }


    /**
     * 爬取  信息列表
     *
     * @return
     */
    @Override
    public List<TTempMessage> getNetMessageList(String msgUrl) {
        log.info("爬取  url为：" + msgUrl + " 的信息。");
        List<TTempMessage> res = new ArrayList<>();

        //一页十条  默认1页
        for (int pageSize = 1; pageSize <= 1; pageSize++) {
            Map param = new HashMap(10);

//            String url = "https://www.pdflibr.com/" + msgUrl + "?page=" + pageSize;
            String url = msgUrl + "?page=" + pageSize;
            param.put("authority", "www.pdflibr.com");
            param.put("method", "GET");
            param.put("path", "/?page=" + pageSize);
            param.put("scheme", "https");
            param.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
            param.put("accept-encoding", "gzip, deflate, br");
            param.put("accept-language", "zh-CN,zh;q=0.9");
            param.put("cache-control", "max-age=0");
            param.put("cookie", "_ga=GA1.2.1392850489.1558767414; _gid=GA1.2.1767409055.1558767414");
            param.put("upgrade-insecure-requests", "1");
            param.put("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.157 Safari/537.36");

            String s = HttpRequestUtil.get(url, param);
            Document document = Jsoup.parse(s);

            JXDocument jxd = new JXDocument(document);

            //正常是10条消息
            List<JXNode> list = jxd.selN("//div[@class=\"container sms-content-list\"]//tbody/tr");
            if (CollectionUtils.isEmpty(list)) {
                return res;
            }
            list.forEach(x -> {
                TTempMessage tm = new TTempMessage();
                List<JXNode> timeNodes = x.sel(".//time/text()");
                if (CollectionUtils.isNotEmpty(timeNodes)) {
                    JXNode jxNode = timeNodes.get(0);
                    String createTime = jxNode == null ? "1999-09-09 09:09:09" : jxNode.getTextVal();
                    LocalDateTime localDateTime = DateUtil.str2LocalDateTime(createTime);
                    tm.setCreateTime(localDateTime);
                }
                List<JXNode> tdNodes = x.sel(".//td/text()");
//                JXNode jxNode1 = tdNodes.get(0);
                if (CollectionUtils.isNotEmpty(tdNodes)) {
                    tm.setPhoneId(tdNodes.get(1) == null ? "" : tdNodes.get(1).getTextVal());
                    tm.setMsgUrl(tdNodes.get(2) == null ? "" : tdNodes.get(2).getTextVal());
                    res.add(tm);
                }

            });
        }
        return res;
    }
//2019-05-28 18:35:14

    /**
     * 爬取  phone列表
     *
     * @return
     */
    @Override
    public List<TTempMessage> getNetList() {

        List<TTempMessage> res = new ArrayList<>();

        int pageSize = 1;
        while (true) {
            Map param = new HashMap();
            String url = "https://www.pdflibr.com/?page=" + pageSize;
            param.put("authority", "www.pdflibr.com");
            param.put("method", "GET");
            param.put("path", "/?page=" + pageSize);
            param.put("scheme", "https");
            param.put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
            param.put("accept-encoding", "gzip, deflate, br");
            param.put("accept-language", "zh-CN,zh;q=0.9");
            param.put("cache-control", "max-age=0");
            param.put("cookie", "_ga=GA1.2.1392850489.1558767414; _gid=GA1.2.1767409055.1558767414");
            param.put("upgrade-insecure-requests", "1");
            param.put("user-agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.157 Safari/537.36");

            String s = HttpRequestUtil.get(url, param);
            Document document = Jsoup.parse(s);


            JXDocument jxd = new JXDocument(document);

            List<JXNode> list = jxd.selN("//div[@class=\"sms-number-list row show-grid container\"]");
            if (CollectionUtils.isEmpty(list)) {
                return res;
            }
            list.forEach(x -> {
                List<JXNode> h3 = x.sel(".//h3[1]/text()");
                JXNode h3Node = h3.get(0);
                String phoneId = h3Node.getTextVal();
                if (phoneId.startsWith("+86")) {
                    phoneId = phoneId.substring(3);
                }
                log.info("phoneId：   " + phoneId);

                List<JXNode> href = x.sel(".//a[@class=\"btn btn-w-m btn-success btn-lg\"]");
                JXNode hrefNode = href.get(0);
                Element hrefElement = hrefNode.getElement();
                String hrefUrl = hrefElement.attr("href");
                String messageUrl = "https://www.pdflibr.com" + hrefUrl;
                log.info("messageUrl:    " + messageUrl);


                TTempMessage tempMessage = new TTempMessage();
                tempMessage.setPhoneId(phoneId);
                tempMessage.setMsgUrl(messageUrl);
                res.add(tempMessage);
            });
            pageSize++;
        }

    }
}
