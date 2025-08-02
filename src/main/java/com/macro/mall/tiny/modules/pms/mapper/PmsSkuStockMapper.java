package com.macro.mall.tiny.modules.pms.mapper;

import com.macro.mall.tiny.modules.pms.model.PmsSkuStock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * sku的库存 Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2025-07-28
 */
public interface PmsSkuStockMapper extends BaseMapper<PmsSkuStock> {
    int replaceList(@Param("list") List<PmsSkuStock> skuStockList);
}
