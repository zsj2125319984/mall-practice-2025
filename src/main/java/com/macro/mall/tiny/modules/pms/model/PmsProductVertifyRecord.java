package com.macro.mall.tiny.modules.pms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品审核记录
 * </p>
 *
 * @author macro
 * @since 2025-07-28
 */
@Getter
@Setter
@TableName("pms_product_vertify_record")
@ApiModel(value = "PmsProductVertifyRecord对象", description = "商品审核记录")
public class PmsProductVertifyRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long productId;

    private Date createTime;

    @ApiModelProperty("审核人")
    private String vertifyMan;

    private Integer status;

    @ApiModelProperty("反馈详情")
    private String detail;


}
