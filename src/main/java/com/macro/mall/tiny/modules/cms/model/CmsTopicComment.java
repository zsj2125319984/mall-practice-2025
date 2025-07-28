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
 * 专题评论表
 * </p>
 *
 * @author Sicecream
 * @since 2025-07-28
 */
@Getter
@Setter
@TableName("cms_topic_comment")
@ApiModel(value = "CmsTopicComment对象", description = "专题评论表")
public class CmsTopicComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String memberNickName;

    private Long topicId;

    private String memberIcon;

    private String content;

    private Date createTime;

    private Integer showStatus;


}
