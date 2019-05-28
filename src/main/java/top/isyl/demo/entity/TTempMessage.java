package top.isyl.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * @since 2019-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TTempMessage对象", description="")
public class TTempMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "手机号")
    @TableId("phone_id")
    private String phoneId;

    @ApiModelProperty(value = "查询消息url")
    @TableField("msg_url")
    private String msgUrl;

    @ApiModelProperty(value = "更新时间")
    @TableField("create_time")
//    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime createTime;


}
