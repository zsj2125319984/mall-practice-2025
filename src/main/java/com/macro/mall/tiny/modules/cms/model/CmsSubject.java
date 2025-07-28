package com.macro.mall.tiny.modules.cms.model;

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
 * 专题表
 * </p>
 *
 * @author Sicecream
 * @since 2025-07-28
 */
@Getter
@Setter
@TableName("cms_subject")
@ApiModel(value = "CmsSubject对象", description = "专题表")
public class CmsSubject implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long categoryId;

    private String title;

    @ApiModelProperty("专题主图")
    private String pic;

    @ApiModelProperty("关联产品数量")
    private Integer productCount;

    private Integer recommendStatus;

    private Date createTime;

    private Integer collectCount;

    private Integer readCount;

    private Integer commentCount;

    @ApiModelProperty("画册图片用逗号分割")
    private String albumPics;

    private String description;

    @ApiModelProperty("显示状态：0->不显示；1->显示")
    private Integer showStatus;

    private String content;

    @ApiModelProperty("转发数")
    private Integer forwardCount;

    @ApiModelProperty("专题分类名称")
    private String categoryName;


}
