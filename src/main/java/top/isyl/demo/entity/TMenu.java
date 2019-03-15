package top.isyl.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author huangyunlong
 * @since 2019-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TMenu对象", description="菜单表")
public class TMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "菜单id")
    @TableId("menu_id")
    private String menuId;

    @ApiModelProperty(value = "父id")
    @TableField("parent_id")
    private String parentId;

    @ApiModelProperty(value = "菜单名称")
    @TableField("menu_name")
    private String menuName;

    @ApiModelProperty(value = "菜单层级")
    @TableField("menu_level")
    private Integer menuLevel;

    @ApiModelProperty(value = "启用状态   0：失效   1：启用")
    @TableField("data_flag")
    private Integer dataFlag;


}
