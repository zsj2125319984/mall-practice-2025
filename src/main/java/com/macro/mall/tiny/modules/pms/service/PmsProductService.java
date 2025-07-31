package com.macro.mall.tiny.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.pms.dto.PmsProductParam;
import com.macro.mall.tiny.modules.pms.dto.PmsProductQueryParam;
import com.macro.mall.tiny.modules.pms.dto.PmsProductResult;
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

    /**
     * 批量设为新品
     * @param ids
     * @param newStatus
     * @return int
     */
    int updateNewStatus(List<Long> ids, Integer newStatus);

    /**
     * 批量修改删除状态
     * @param ids
     * @param deleteStatus
     * @return int
     */
    int updateDeleteStatus(List<Long> ids, Integer deleteStatus);

    /**
     * 批量上下架商品
     * @param ids
     * @param publishStatus
     * @return int
     */
    int updatePublishStatus(List<Long> ids, Integer publishStatus);

    /**
     * 批量推荐商品
     * @param ids
     * @param recommendStatus
     * @return int
     */
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    /**
     * 根据商品id获取商品编辑信息
     * @param id
     * @return {@link PmsProductResult }
     */
    PmsProductResult updateInfo(Long id);

    /**
     * 批量修改审核状态
     * @param detail
     * @param ids
     * @param verifyStatus
     * @return int
     */
    @Transactional
    int updateVerifyStatus(String detail, List<Long> ids, Integer verifyStatus);

    /**
     * 更新商品
     * @param id
     * @param productParam
     * @return int
     */
    @Transactional
    int update(Long id, PmsProductParam productParam);
}
