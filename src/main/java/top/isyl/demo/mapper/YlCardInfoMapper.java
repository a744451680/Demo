package top.isyl.demo.mapper;

import org.apache.ibatis.annotations.Select;
import top.isyl.demo.entity.YlCardInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 身份信息表 Mapper 接口
 * </p>
 *
 * @author hyl
 * @since 2019-05-01
 */
public interface YlCardInfoMapper extends BaseMapper<YlCardInfo> {

    @Select("select substr(card,7,4) sub from yl_card_info where length(card) = 18 GROUP BY sub")
    List<String> getCaedYears();
}
