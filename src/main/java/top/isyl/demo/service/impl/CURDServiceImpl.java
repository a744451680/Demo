package top.isyl.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.isyl.demo.service.CURDService;
import top.isyl.demo.entity.CURDInfo;
import top.isyl.demo.mapper.CURDMapper;

import java.util.List;

/**
 * @Author huangyunlong
 * @Date 2019/2/28
 */
@Service
public class CURDServiceImpl extends ServiceImpl<CURDMapper, CURDInfo> implements CURDService {
    @Override
    public List<CURDInfo> selectInfoList() {
        List<CURDInfo> list = this.list();
        return list;
    }

    /**
     * 注解方式
     *
     * @param id
     * @return
     */
    @Override
    public CURDInfo getInfoById(String id) {
        CURDInfo curdInfo = this.baseMapper.getInfoById(id);
        return curdInfo;
    }

    /**
     * xml配置文件方式
     *
     * @return
     */
    @Override
    public String selectCount() {
        return this.baseMapper.selectCount();
    }


}
