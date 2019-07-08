package top.isyl.demo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.isyl.demo.entity.YlCardInfo;
import top.isyl.demo.mapper.YlCardInfoMapper;
import top.isyl.demo.service.IYlCardInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 身份信息表 服务实现类
 * </p>
 *
 * @author hyl
 * @since 2019-05-01
 */
@Service
public class YlCardInfoServiceImpl extends ServiceImpl<YlCardInfoMapper, YlCardInfo> implements IYlCardInfoService {

    /**
     * 获取随机身份信息
     *
     * @return
     */
    @Override
    public YlCardInfo getRandomCard() {

        int count = this.count();

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.last("limit " + new Random().nextInt(count) + ",1");
        YlCardInfo cardInfo = this.getOne(queryWrapper);

        return cardInfo;
    }

    /**
     * 获取身份信息 年份 列表
     */
    @Override
    public List<String> getCaedYears() {
        List<String> years = this.baseMapper.getCaedYears();
        return years;
    }

    /**
     * 分页查询身份信息
     */
    @Override
    public IPage<YlCardInfo> cardPageList(String name, String startDate, String endDate, Integer pageNum, Integer pageSize) {

        // 日期 转换成数字 ， 20180808
        QueryWrapper<YlCardInfo> qw = new QueryWrapper<>();
        //动态拼接，姓名
        if (!StringUtils.isEmpty(name)) {
            qw.like("name", name);
        }
        //起始日期
        if (ObjectUtil.isNotNull(startDate)) {
            qw.ge("substr(card,7,8)", startDate);
        }
        //结束日期
        if (ObjectUtil.isNotNull(endDate)) {
            qw.le("substr(card,7,8)", endDate);
        }
        qw.orderByAsc("substr( card, 7,8 )");
        IPage<YlCardInfo> page = this.page(new Page<>(pageNum, pageSize), qw);

        return page;

    }
}
