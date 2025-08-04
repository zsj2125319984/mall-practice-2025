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
 * 限时购通知记录
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-04
 */
@Getter
@Setter
@TableName("sms_flash_promotion_log")
@ApiModel(value = "SmsFlashPromotionLog对象", description = "限时购通知记录")
public class SmsFlashPromotionLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer memberId;

    private Long productId;

    private String memberPhone;

    private String productName;

    @ApiModelProperty("会员订阅时间")
    private Date subscribeTime;

    private Date sendTime;


}
