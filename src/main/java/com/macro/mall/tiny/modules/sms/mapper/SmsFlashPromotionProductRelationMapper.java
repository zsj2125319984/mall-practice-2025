package com.macro.mall.tiny.modules.sms.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.sms.dto.SmsFlashPromotionProduct;
import com.macro.mall.tiny.modules.sms.model.SmsFlashPromotionProductRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品限时购与商品关系表 Mapper 接口
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-04
 */
public interface SmsFlashPromotionProductRelationMapper extends BaseMapper<SmsFlashPromotionProductRelation> {
    /**
     * 获取限时购及相关商品信息
     */
    IPage<SmsFlashPromotionProduct> getList(IPage<SmsFlashPromotionProduct> page,
                                            @Param("flashPromotionId") Long flashPromotionId,
                                            @Param("flashPromotionSessionId") Long flashPromotionSessionId);
}
