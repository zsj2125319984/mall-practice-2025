package com.macro.mall.tiny.modules.pms.model;

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
 * 相册表
 * </p>
 *
 * @author macro
 * @since 2025-07-28
 */
@Getter
@Setter
@TableName("pms_album")
@ApiModel(value = "PmsAlbum对象", description = "相册表")
public class PmsAlbum implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String coverPic;

    private Integer picCount;

    private Integer sort;

    private String description;


}
