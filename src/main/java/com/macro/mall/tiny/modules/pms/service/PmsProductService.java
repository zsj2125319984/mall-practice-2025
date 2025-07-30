package com.macro.mall.tiny.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.pms.dto.PmsProductParam;
import com.macro.mall.tiny.modules.pms.dto.PmsProductQueryParam;
import com.macro.mall.tiny.modules.pms.model.PmsProduct;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品信息 服务类
 * </p>
 *
 * @author macro
 * @since 2025-07-28
 */
public interface PmsProductService extends IService<PmsProduct> {

    /**
     * 创建商品
     * @param pmsProductParam
     * @return int
     */
    @Transactional
    int create(PmsProductParam pmsProductParam);

    /**
     * 查询商品
     * @param productQueryParam
     * @param pageNum
     * @param pageSize
     * @return {@link Page }<{@link PmsProduct }>
     */
    Page<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageNum, Integer pageSize);

    /**
     * 根据商品名称或货号模糊查询
     * @param keyword
     * @return {@link List }<{@link PmsProduct }>
     */
    List<PmsProduct> list(String keyword);
}
