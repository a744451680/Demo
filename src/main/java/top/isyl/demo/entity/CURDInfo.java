package top.isyl.demo.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author huangyunlong
 * @Date 2019/2/27
 */
@Data
@TableName("t_curd_info")
@Accessors(chain = true)
@ApiModel(value = "测试实体类")
public class CURDInfo {

    /**
     * Id
     */
    @ApiModelProperty(value = "编号")
    @Excel(name = "编号")
    @TableId("id")
    private String id;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @Excel(name = "名称")
    @TableField("name")
    private String name;
    /**
     * asd
     */
    @ApiModelProperty(value = "comment")
    @TableField("comment")
    private String comment;

}
