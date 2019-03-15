package top.isyl.demo.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author huangyunlong
 * @Date 2019/1/9
 */
public class CopyUtil {


    /**
     * List泛型转换
     * @param list
     * @param target
     * @return
     */
    public static List<?> copyPropertiesList(List<?> list, Class target) {
        List<Object> result = new ArrayList();
        if (list != null) {
            for (Object o : list) {
                try {
                    Object d = target.newInstance();
                    BeanUtils.copyProperties(o, d);
                    result.add(d);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
