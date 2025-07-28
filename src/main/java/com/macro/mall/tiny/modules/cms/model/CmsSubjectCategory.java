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
 * 专题分类表
 * </p>
 *
 * @author Sicecream
 * @since 2025-07-28
 */
@Getter
@Setter
@TableName("cms_subject_category")
@ApiModel(value = "CmsSubjectCategory对象", description = "专题分类表")
public class CmsSubjectCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    @ApiModelProperty("分类图标")
    private String icon;

    @ApiModelProperty("专题数量")
    private Integer subjectCount;

    private Integer showStatus;

    private Integer sort;


}
