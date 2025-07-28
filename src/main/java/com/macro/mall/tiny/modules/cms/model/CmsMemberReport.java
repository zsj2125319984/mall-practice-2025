package com.macro.mall.tiny.modules.cms.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户举报表
 * </p>
 *
 * @author Sicecream
 * @since 2025-07-28
 */
@Getter
@Setter
@TableName("cms_member_report")
@ApiModel(value = "CmsMemberReport对象", description = "用户举报表")
public class CmsMemberReport implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty("举报类型：0->商品评价；1->话题内容；2->用户评论")
    private Integer reportType;

    @ApiModelProperty("举报人")
    private String reportMemberName;

    private Date createTime;

    private String reportObject;

    @ApiModelProperty("举报状态：0->未处理；1->已处理")
    private Integer reportStatus;

    @ApiModelProperty("处理结果：0->无效；1->有效；2->恶意")
    private Integer handleStatus;

    private String note;


}
