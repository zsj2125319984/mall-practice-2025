package com.macro.mall.tiny.modules.oms.model;

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
 * 公司收发货地址表
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-02
 */
@Getter
@Setter
@TableName("oms_company_address")
@ApiModel(value = "OmsCompanyAddress对象", description = "公司收发货地址表")
public class OmsCompanyAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("地址名称")
    private String addressName;

    @ApiModelProperty("默认发货地址：0->否；1->是")
    private Integer sendStatus;

    @ApiModelProperty("是否默认收货地址：0->否；1->是")
    private Integer receiveStatus;

    @ApiModelProperty("收发货人姓名")
    private String name;

    @ApiModelProperty("收货人电话")
    private String phone;

    @ApiModelProperty("省/直辖市")
    private String province;

    @ApiModelProperty("市")
    private String city;

    @ApiModelProperty("区")
    private String region;

    @ApiModelProperty("详细地址")
    private String detailAddress;


}
