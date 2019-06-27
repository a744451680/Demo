package top.isyl.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.isyl.demo.entity.YlCardInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    /**
     * 获取身份信息 年份 列表
     */
    List<String> getCaedYears();


    /**
     * 分页查询身份信息
     */
    IPage<YlCardInfo> cardPageList(String name, Integer year, Integer pageNum, Integer pageSize);
}
