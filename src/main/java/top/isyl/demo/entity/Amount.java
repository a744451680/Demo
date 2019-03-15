package top.isyl.demo.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;


/**
 * @author: huangyunlong
 * @date: 2018/12/11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "金额")
public class Amount {

    /**
     * 数量
     */
    @Excel(name = "数量")
    @ApiModelProperty(value = "数量")
    private String number;

    /**
     * 金额
     */
    @Excel(name = "金额")
    @ApiModelProperty(value = "金额")
    private String startBalance;

    /**
     * 原币
     */
/*    @Excel(name = "原币")
    @ApiModelProperty(value = "原币")
    private String startBalanceForeign;*/



}
