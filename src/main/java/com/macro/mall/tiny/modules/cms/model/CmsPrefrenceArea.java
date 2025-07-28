package com.macro.mall.tiny.modules.cms.model;

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
 * 优选专区
 * </p>
 *
 * @author Sicecream
 * @since 2025-07-28
 */
@Getter
@Setter
@TableName("cms_prefrence_area")
@ApiModel(value = "CmsPrefrenceArea对象", description = "优选专区")
public class CmsPrefrenceArea implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String subTitle;

    @ApiModelProperty("展示图片")
    private byte[] pic;

    private Integer sort;

    private Integer showStatus;


}
