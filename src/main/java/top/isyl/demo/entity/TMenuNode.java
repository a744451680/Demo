package top.isyl.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

/**
 * @Author huangyunlong
 * @Date 2019/3/12
 */

public class TMenuNode extends Node {


    private static final long serialVersionUID = 1L;

    public TMenuNode(TMenu menu){

        this.setId(menu.getMenuId());
        this.setParentId(menu.getParentId());
        this.menuId = menu.getMenuId();
        this.parentId = menu.getParentId();
        this.menuName = menu.getMenuName();
        this.menuLevel = menu.getMenuLevel();
        this.dataFlag = menu.getDataFlag();
    }


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

/**
     * 返回menu对象
     * @return
     */

    public TMenu  toTMenu(){
        TMenu menu = new TMenu();
        BeanUtils.copyProperties(this,menu);
        return menu;
    }
}
