package com.macro.mall.tiny.modules.pms.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品信息
 * </p>
 *
 * @author macro
 * @since 2025-07-28
 */
@Getter
@Setter
@TableName("pms_product")
@ApiModel(value = "PmsProduct对象", description = "商品信息")
public class PmsProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long brandId;

    private Long productCategoryId;

    private Long feightTemplateId;

    private Long productAttributeCategoryId;

    private String name;

    private String pic;

    @ApiModelProperty("货号")
    private String productSn;

    @ApiModelProperty("删除状态：0->未删除；1->已删除")
    private Integer deleteStatus;

    @ApiModelProperty("上架状态：0->下架；1->上架")
    private Integer publishStatus;

    @ApiModelProperty("新品状态:0->不是新品；1->新品")
    private Integer newStatus;

    @ApiModelProperty("推荐状态；0->不推荐；1->推荐")
    private Integer recommandStatus;

    @ApiModelProperty("审核状态：0->未审核；1->审核通过")
    private Integer verifyStatus;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("销量")
    private Integer sale;

    private BigDecimal price;

    @ApiModelProperty("促销价格")
    private BigDecimal promotionPrice;

    @ApiModelProperty("赠送的成长值")
    private Integer giftGrowth;

    @ApiModelProperty("赠送的积分")
    private Integer giftPoint;

    @ApiModelProperty("限制使用的积分数")
    private Integer usePointLimit;

    @ApiModelProperty("副标题")
    private String subTitle;

    @ApiModelProperty("商品描述")
    private String description;

    @ApiModelProperty("市场价")
    private BigDecimal originalPrice;

    @ApiModelProperty("库存")
    private Integer stock;

    @ApiModelProperty("库存预警值")
    private Integer lowStock;

    @ApiModelProperty("单位")
    private String unit;

    @ApiModelProperty("商品重量，默认为克")
    private BigDecimal weight;

    @ApiModelProperty("是否为预告商品：0->不是；1->是")
    private Integer previewStatus;

    @ApiModelProperty("以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮")
    private String serviceIds;

    private String keywords;

    private String note;

    @ApiModelProperty("画册图片，连产品图片限制为5张，以逗号分割")
    private String albumPics;

    private String detailTitle;

    private String detailDesc;

    @ApiModelProperty("产品详情网页内容")
    private String detailHtml;

    @ApiModelProperty("移动端网页详情")
    private String detailMobileHtml;

    @ApiModelProperty("促销开始时间")
    private Date promotionStartTime;

    @ApiModelProperty("促销结束时间")
    private Date promotionEndTime;

    @ApiModelProperty("活动限购数量")
    private Integer promotionPerLimit;

    @ApiModelProperty("促销类型：0->没有促销使用原价;1->使用促销价；2->使用会员价；3->使用阶梯价格；4->使用满减价格；5->限时购")
    private Integer promotionType;

    @ApiModelProperty("品牌名称")
    private String brandName;

    @ApiModelProperty("商品分类名称")
    private String productCategoryName;


}
