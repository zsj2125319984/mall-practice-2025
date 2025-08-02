package com.macro.mall.tiny.modules.pms.service;

import com.macro.mall.tiny.modules.pms.model.PmsSkuStock;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * sku的库存 服务类
 * </p>
 *
 * @author macro
 * @since 2025-07-28
 */
public interface PmsSkuStockService extends IService<PmsSkuStock> {
    /**
     * 根据商品id和skuCode关键字模糊搜索
     */
    List<PmsSkuStock> getList(Long pid, String keyword);

    /**
     * 批量更新商品库存信息
     */
    int update(Long pid, List<PmsSkuStock> skuStockList);
}
