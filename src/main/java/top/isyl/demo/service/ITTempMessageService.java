package top.isyl.demo.service;

import top.isyl.demo.entity.TTempMessage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hyl
 * @since 2019-05-27
 */
public interface ITTempMessageService extends IService<TTempMessage> {


    List<TTempMessage> getNetMessageList(String msgUrl);

    /**
     * 获取列表
     */
    List<TTempMessage> getNetList();


    /**
     * 获取手机号列表
     *
     * @return
     */
    List<String> getPhoneIds();

    /**
     * 保存临时短信info
     *
     * @return
     */
    void saveTempMessageInfos();

    /**
     * 查询信息列表
     * @param phoneId
     * @return
     */
    List<TTempMessage> getMessage(String phoneId);
}
