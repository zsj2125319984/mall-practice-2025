package com.macro.mall.tiny.modules.sms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.sms.model.SmsHomeNewProduct;
import com.macro.mall.tiny.modules.sms.mapper.SmsHomeNewProductMapper;
import com.macro.mall.tiny.modules.sms.service.SmsHomeNewProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 新鲜好物表 服务实现类
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-04
 */
@Service
public class SmsHomeNewProductServiceImpl extends ServiceImpl<SmsHomeNewProductMapper, SmsHomeNewProduct> implements SmsHomeNewProductService {

    @Override
    public int create(List<SmsHomeNewProduct> homeNewProductList) {
        for (SmsHomeNewProduct SmsHomeNewProduct : homeNewProductList) {
            SmsHomeNewProduct.setRecommendStatus(1);
            SmsHomeNewProduct.setSort(0);
            save(SmsHomeNewProduct);
        }
        return homeNewProductList.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeNewProduct homeNewProduct = new SmsHomeNewProduct();
        homeNewProduct.setId(id);
        homeNewProduct.setSort(sort);
        return updateById(homeNewProduct) ? 1 : 0;
    }

    @Override
    public int delete(List<Long> ids) {
        QueryWrapper<SmsHomeNewProduct> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(SmsHomeNewProduct::getId,ids);

        return remove(wrapper) ? ids.size() : 0;
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeNewProduct record = new SmsHomeNewProduct();
        record.setRecommendStatus(recommendStatus);

        QueryWrapper<SmsHomeNewProduct> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(SmsHomeNewProduct::getId,ids);

        return update(record,wrapper) ? ids.size() : 0;
    }

    @Override
    public Page<SmsHomeNewProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        Page<SmsHomeNewProduct> page = new Page<>(pageNum,pageSize);
        QueryWrapper<SmsHomeNewProduct> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<SmsHomeNewProduct> lambda = wrapper.lambda();
        if(StrUtil.isNotEmpty(productName)){
            lambda.and(w ->
                    w.like(SmsHomeNewProduct::getProductName,productName));
        }
        if(recommendStatus != null){
            lambda.and(w ->
                    w.eq(SmsHomeNewProduct::getRecommendStatus,recommendStatus));
        }

        return page(page,wrapper);
    }
}
