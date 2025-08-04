package com.macro.mall.tiny.modules.sms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 新鲜好物表
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-04
 */
@Getter
@Setter
@TableName("sms_home_new_product")
@ApiModel(value = "SmsHomeNewProduct对象", description = "新鲜好物表")
public class SmsHomeNewProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long productId;

    private String productName;

    private Integer recommendStatus;

    private Integer sort;


}
