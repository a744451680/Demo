package top.isyl.demo.service;

import top.isyl.demo.entity.TWxMenu;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 微信菜单表 服务类
 * </p>
 *
 * @author hyl
 * @since 2019-04-22
 */
public interface ITWxMenuService extends IService<TWxMenu> {

    /**
     * 创建微信菜单
     */
    void createWxMenux();
    /**
     * 查询微信菜单
     */
    String getWxMenux();
    /**
     *  删除微信菜单
     */
    void delectWxMenu();
}
