package top.isyl.demo.service.impl;

import top.isyl.demo.entity.TCounter;
import top.isyl.demo.mapper.TCounterMapper;
import top.isyl.demo.service.ITCounterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 计数器 服务实现类
 * </p>
 *
 * @author huangyunlong
 * @since 2019-03-11
 */
@Service
public class TCounterServiceImpl extends ServiceImpl<TCounterMapper, TCounter> implements ITCounterService {

}
