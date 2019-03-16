package top.isyl.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.isyl.demo.entity.CURDInfo;

import java.util.List;

/**
 * @Author huangyunlong
 * @Date 2019/2/28
 */
public interface CURDService extends IService<CURDInfo> {

    List<CURDInfo> selectInfoList();

    CURDInfo getInfoById(String id);

    String selectCount();

    IPage<CURDInfo> selectInfoPage(Integer pageNum, Integer pageSize);
}
