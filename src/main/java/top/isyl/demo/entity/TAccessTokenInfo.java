package top.isyl.demo.entity;

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
 * 
 * </p>
 *
 * @author hyl
 * @since 2019-04-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TAccessTokenInfo对象", description="")
public class TAccessTokenInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId("token_id")
    private String tokenId;

    @ApiModelProperty(value = "token")
    @TableField("access_token")
    private String accessToken;

    @ApiModelProperty(value = "过期时间")
    @TableField("expires_in")
    private String expiresIn;

    @ApiModelProperty(value = "创建时间")
    @TableField("reg_time")
    private String regTime;


}
