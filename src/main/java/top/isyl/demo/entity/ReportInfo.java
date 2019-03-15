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
 * 报表测试类
 * @author: IT_Yl
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "ReportInfo  报表测试类")
public class ReportInfo {


    /**
     * Excel
     *      ：isColumnHidden = true   是否隐藏列
     *      ： groupName = "xxx"     组名称
     *      : format = "yyyy/MM/dd HH:mm"  格式化
     */




    /**
     * 名称
     */
    @Excel(name = "名称")
    @ApiModelProperty(value = "名称")
    private String ame;

    /**
     * 编码
     */
    @Excel(name = "编码")
    @ApiModelProperty(value = "编码")
    private String actSubjectCode;

    /**
     * 方向 1 正 -1 负
     */
    @Excel(name = "方向", replace = {"正_1", "负_-1"})
    @ApiModelProperty(value = "方向 1 借 -1 贷")
    private Integer actSubjectDirection;


    /**
     * 类型1 数量
     */
    @ApiModelProperty(value = "类型1 数量")
    @Excel(name = "数量", groupName = "类型1",orderNum = "1")
    private Integer auxiliaryNumStart;
    /**
     * 类型1 金额
     */
    @ApiModelProperty(value = "类型1 金额")
    @Excel(name = "金额", groupName = "类型1",orderNum = "2")
    private BigDecimal startBalance;


    /**
     * 类型2 数量
     */
    @ApiModelProperty(value = "类型2  数量")
    @Excel(name = "数量", groupName = "类型2",orderNum = "1")
    private Integer auxiliaryNumDebit;
    /**
     * 类型2 金额
     */
    @ApiModelProperty(value = "类型2 金额")
    @Excel(name = "金额", groupName = "类型2",orderNum = "2")
    private BigDecimal debitSum;



    /**
     * 类型3 数量
     */
    @ApiModelProperty(value = "类型3 数量")
    @Excel(name = "数量", groupName = "类型3",orderNum = "1")
    private Integer auxiliaryNumCredit;

    /**
     * 类型3 金额
     */
    @ApiModelProperty(value = "类型3 金额")
    @Excel(name = "金额", groupName = "类型3",orderNum = "2")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal creditSum;



}
