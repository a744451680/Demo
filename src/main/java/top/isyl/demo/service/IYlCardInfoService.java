package top.isyl.demo.service;

import top.isyl.demo.entity.YlCardInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 身份信息表 服务类
 * </p>
 *
 * @author hyl
 * @since 2019-05-01
 */
public interface IYlCardInfoService extends IService<YlCardInfo> {

    /**
     * 获取随机身份信息;
     * @return
     */
    YlCardInfo getRandomCard();
}
