package com.macro.mall.tiny.modules.pms.mapper;

import com.macro.mall.tiny.modules.pms.dto.PmsProductResult;
import com.macro.mall.tiny.modules.pms.model.PmsProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 商品信息 Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2025-07-28
 */
public interface PmsProductMapper extends BaseMapper<PmsProduct> {
    /**
     * 获取商品编辑信息
     * @param id
     * @return {@link PmsProductResult }
     */
    PmsProductResult getUpdateInfo(Long id);
}
