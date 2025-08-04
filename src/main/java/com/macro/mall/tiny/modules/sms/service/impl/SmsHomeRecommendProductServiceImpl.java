package com.macro.mall.tiny.modules.sms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.sms.model.SmsHomeRecommendProduct;
import com.macro.mall.tiny.modules.sms.mapper.SmsHomeRecommendProductMapper;
import com.macro.mall.tiny.modules.sms.service.SmsHomeRecommendProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 人气推荐商品表 服务实现类
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-04
 */
@Service
public class SmsHomeRecommendProductServiceImpl extends ServiceImpl<SmsHomeRecommendProductMapper, SmsHomeRecommendProduct> implements SmsHomeRecommendProductService {

    @Override
    public int create(List<SmsHomeRecommendProduct> homeRecommendProductList) {
        for (SmsHomeRecommendProduct recommendProduct : homeRecommendProductList) {
            recommendProduct.setRecommendStatus(1);
            recommendProduct.setSort(0);
            save(recommendProduct);
        }
        return homeRecommendProductList.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeRecommendProduct recommendProduct = new SmsHomeRecommendProduct();
        recommendProduct.setId(id);
        recommendProduct.setSort(sort);
        return updateById(recommendProduct) ? 1 : 0;
    }

    @Override
    public int delete(List<Long> ids) {
        QueryWrapper<SmsHomeRecommendProduct> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(SmsHomeRecommendProduct::getId,ids);

        return remove(wrapper) ? 1 : 0;
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeRecommendProduct record = new SmsHomeRecommendProduct();
        record.setRecommendStatus(recommendStatus);

        QueryWrapper<SmsHomeRecommendProduct> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(SmsHomeRecommendProduct::getId,ids);

        return update(record,wrapper) ? ids.size() : 0;
    }

    @Override
    public Page<SmsHomeRecommendProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        Page<SmsHomeRecommendProduct> page = new Page<>(pageNum,pageSize);
        QueryWrapper<SmsHomeRecommendProduct> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<SmsHomeRecommendProduct> lambda = wrapper.lambda();
        if(!StrUtil.isEmpty(productName)){
            lambda.and(w ->
                    w.like(SmsHomeRecommendProduct::getProductName,productName));
        }
        if(recommendStatus!=null){
            lambda.and(w ->
                    w.eq(SmsHomeRecommendProduct::getRecommendStatus,recommendStatus));
        }
        wrapper.orderByDesc("sort");

        return page(page,wrapper);
    }
}
