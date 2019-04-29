package top.isyl.demo.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 微信菜单表
 * </p>
 *
 * @author hyl
 * @since 2019-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TWxMenu对象", description="微信菜单表")
public class TWxMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "微信菜单json")
    @TableField("wx_menu_json")
    private String wxMenuJson;

    @ApiModelProperty(value = "描述")
    @TableField("menu_desc")
    private String menuDesc;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;


}
