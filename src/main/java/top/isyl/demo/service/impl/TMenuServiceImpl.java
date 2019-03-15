package top.isyl.demo.service.impl;

import top.isyl.demo.entity.TMenu;
import top.isyl.demo.mapper.TMenuMapper;
import top.isyl.demo.service.ITMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author huangyunlong
 * @since 2019-03-12
 */
@Service
public class TMenuServiceImpl extends ServiceImpl<TMenuMapper, TMenu> implements ITMenuService {

}
