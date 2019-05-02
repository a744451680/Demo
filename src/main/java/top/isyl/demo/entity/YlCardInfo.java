package top.isyl.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 身份信息表
 * </p>
 *
 * @author hyl
 * @since 2019-05-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="YlCardInfo对象", description="身份信息表")
public class YlCardInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("phone")
    private String phone;

    @ApiModelProperty(value = "身份证号")
    @TableField("card")
    private String card;

    @ApiModelProperty(value = "姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "邮箱")
    @TableField("mail")
    private String mail;

    @ApiModelProperty(value = "密码")
    @TableField("pwd")
    private String pwd;

    @ApiModelProperty(value = "密码2")
    @TableField("pwd2")
    private String pwd2;

    @ApiModelProperty(value = "密码3")
    @TableField("pwd3")
    private String pwd3;

    @ApiModelProperty(value = "弱密码   0：不是弱密码   1：弱密码")
    @TableField("weak_pwd")
    private Integer weakPwd;


}
