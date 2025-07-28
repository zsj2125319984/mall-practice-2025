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
 * 商品评价表
 * </p>
 *
 * @author macro
 * @since 2025-07-28
 */
@Getter
@Setter
@TableName("pms_comment")
@ApiModel(value = "PmsComment对象", description = "商品评价表")
public class PmsComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long productId;

    private String memberNickName;

    private String productName;

    @ApiModelProperty("评价星数：0->5")
    private Integer star;

    @ApiModelProperty("评价的ip")
    private String memberIp;

    private Date createTime;

    private Integer showStatus;

    @ApiModelProperty("购买时的商品属性")
    private String productAttribute;

    private Integer collectCouont;

    private Integer readCount;

    private String content;

    @ApiModelProperty("上传图片地址，以逗号隔开")
    private String pics;

    @ApiModelProperty("评论用户头像")
    private String memberIcon;

    private Integer replayCount;


}
