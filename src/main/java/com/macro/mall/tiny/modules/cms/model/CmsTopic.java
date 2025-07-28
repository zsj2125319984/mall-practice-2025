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
 * 话题表
 * </p>
 *
 * @author Sicecream
 * @since 2025-07-28
 */
@Getter
@Setter
@TableName("cms_topic")
@ApiModel(value = "CmsTopic对象", description = "话题表")
public class CmsTopic implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long categoryId;

    private String name;

    private Date createTime;

    private Date startTime;

    private Date endTime;

    @ApiModelProperty("参与人数")
    private Integer attendCount;

    @ApiModelProperty("关注人数")
    private Integer attentionCount;

    private Integer readCount;

    @ApiModelProperty("奖品名称")
    private String awardName;

    @ApiModelProperty("参与方式")
    private String attendType;

    @ApiModelProperty("话题内容")
    private String content;


}
