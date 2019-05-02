package top.isyl.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
     * @return
     */
    @Override
    public YlCardInfo getRandomCard() {

        int count = this.count();

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.last("limit "+new Random().nextInt(count)+",1" );
        YlCardInfo cardInfo = this.getOne(queryWrapper);

        return cardInfo;
    }
}
