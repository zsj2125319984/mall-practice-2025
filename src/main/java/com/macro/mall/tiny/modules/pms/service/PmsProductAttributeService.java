package com.macro.mall.tiny.modules.pms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.pms.dto.PmsProductAttributeParam;
import com.macro.mall.tiny.modules.pms.dto.ProductAttrInfo;
import com.macro.mall.tiny.modules.pms.model.PmsProductAttribute;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 商品属性参数表 服务类
 * </p>
 *
 * @author macro
 * @since 2025-07-28
 */
public interface PmsProductAttributeService extends IService<PmsProductAttribute> {
    /**
     * 根据分类ID和类型分页获取商品属性
     * @param cid 分类id
     * @param type 0->规格；1->参数
     */
    Page<PmsProductAttribute> getList(Long cid, Integer type, Integer pageSize, Integer pageNum);

    /**
     * 添加商品属性
     */
    @Transactional
    int create(PmsProductAttributeParam pmsProductAttributeParam);

    /**
     * 修改商品属性
     */
    int update(Long id, PmsProductAttributeParam productAttributeParam);

    /**
     * 获取单个商品属性信息
     */
    PmsProductAttribute getItem(Long id);

    /**
     * 批量删除商品属性
     */
    @Transactional
    int delete(List<Long> ids);

    /**
     * 获取商品分类对应属性列表
     */
    List<ProductAttrInfo> getProductAttrInfo(Long productCategoryId);
}
