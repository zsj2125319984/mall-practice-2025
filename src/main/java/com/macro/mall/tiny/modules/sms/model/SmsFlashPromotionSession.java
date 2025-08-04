package com.macro.mall.tiny.modules.sms.model;

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
 * 限时购场次表
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-04
 */
@Getter
@Setter
@TableName("sms_flash_promotion_session")
@ApiModel(value = "SmsFlashPromotionSession对象", description = "限时购场次表")
public class SmsFlashPromotionSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("场次名称")
    private String name;

    @ApiModelProperty("每日开始时间")
    private Date startTime;

    @ApiModelProperty("每日结束时间")
    private Date endTime;

    @ApiModelProperty("启用状态：0->不启用；1->启用")
    private Integer status;

    @ApiModelProperty("创建时间")
    private Date createTime;


}
