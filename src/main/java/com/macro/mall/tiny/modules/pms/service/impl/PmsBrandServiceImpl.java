package com.macro.mall.tiny.modules.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.pms.dto.PmsBrandParam;
import com.macro.mall.tiny.modules.pms.model.PmsBrand;
import com.macro.mall.tiny.modules.pms.mapper.PmsBrandMapper;
import com.macro.mall.tiny.modules.pms.model.PmsProduct;
import com.macro.mall.tiny.modules.pms.service.PmsBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.macro.mall.tiny.modules.pms.service.PmsProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author macro
 * @since 2025-07-28
 */
@Service
public class PmsBrandServiceImpl extends ServiceImpl<PmsBrandMapper, PmsBrand> implements PmsBrandService {

    @Autowired
    PmsProductService productService;

    @Override
    public List<PmsBrand> listAllBrand() {
        return list();
    }

    @Override
    public int createBrand(PmsBrandParam pmsBrandParam) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandParam, pmsBrand);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StrUtil.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        return save(pmsBrand) ? 1 : 0;
    }

    @Override
    public int updateBrand(Long id, PmsBrandParam pmsBrandParam) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandParam, pmsBrand);
        pmsBrand.setId(id);
        //如果创建时首字母为空，取名称的第一个为首字母
        if (StrUtil.isEmpty(pmsBrand.getFirstLetter())) {
            pmsBrand.setFirstLetter(pmsBrand.getName().substring(0, 1));
        }
        //更新品牌时要更新商品中的品牌名称
        PmsProduct product = new PmsProduct();
        product.setBrandName(pmsBrand.getName());

        QueryWrapper<PmsProduct> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(PmsProduct::getBrandId,id);
        productService.update(product,wrapper);

        return updateById(pmsBrand) ? 1 : 0;
    }

    @Override
    public int deleteBrand(Long id) {
        return removeById(id) ? 1 : 0;
    }

    @Override
    public int deleteBrand(List<Long> ids) {
        QueryWrapper<PmsBrand> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(PmsBrand::getId,ids);

        return remove(wrapper) ? 1 : 0;
    }

    @Override
    public Page<PmsBrand> listBrand(String keyword, Integer showStatus, int pageNum, int pageSize) {
        Page<PmsBrand> page = new Page<>(pageNum,pageSize);
        QueryWrapper<PmsBrand> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("sort");

        if(StrUtil.isNotEmpty(keyword)){
            wrapper.lambda().and(w ->
                    w.like(PmsBrand::getName,keyword));
        }
        if(showStatus != null){
            wrapper.lambda().and(w ->
                    w.eq(PmsBrand::getShowStatus,showStatus));
        }

        return page(page,wrapper);
    }

    @Override
    public PmsBrand getBrand(Long id) {
        return getById(id);
    }

    @Override
    public int updateShowStatus(List<Long> ids, Integer showStatus) {
        PmsBrand pmsBrand = new PmsBrand();
        pmsBrand.setShowStatus(showStatus);

        QueryWrapper<PmsBrand> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(PmsBrand::getId,ids);

        return update(pmsBrand,wrapper) ? 1 : 0;
    }

    @Override
    public int updateFactoryStatus(List<Long> ids, Integer factoryStatus) {
        PmsBrand pmsBrand = new PmsBrand();
        pmsBrand.setFactoryStatus(factoryStatus);

        QueryWrapper<PmsBrand> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(PmsBrand::getId,ids);

        return update(pmsBrand,wrapper) ? 1 : 0;
    }
}
