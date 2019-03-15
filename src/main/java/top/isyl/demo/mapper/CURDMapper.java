package top.isyl.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.isyl.demo.entity.CURDInfo;

/**
 * @Author huangyunlong
 * @Date 2019/2/28
 */
public interface CURDMapper  extends BaseMapper<CURDInfo> {


    @Select("SELECT * FROM t_curd_info where id = #{id}")
    CURDInfo getInfoById(@Param("id") String id);

    String selectCount();
}
