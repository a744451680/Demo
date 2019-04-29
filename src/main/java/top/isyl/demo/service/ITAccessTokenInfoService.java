package top.isyl.demo.service;

import top.isyl.demo.entity.TAccessTokenInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hyl
 * @since 2019-04-22
 */
public interface ITAccessTokenInfoService extends IService<TAccessTokenInfo> {

    /**
     * 更新AccessToken
     */
    void updateAccessToken();
}
