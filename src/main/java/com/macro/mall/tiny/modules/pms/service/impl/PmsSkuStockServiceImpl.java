package com.macro.mall.tiny.modules.pms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.macro.mall.tiny.modules.pms.model.PmsSkuStock;
import com.macro.mall.tiny.modules.pms.mapper.PmsSkuStockMapper;
import com.macro.mall.tiny.modules.pms.service.PmsSkuStockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * sku的库存 服务实现类
 * </p>
 *
 * @author macro
 * @since 2025-07-28
 */
@Service
public class PmsSkuStockServiceImpl extends ServiceImpl<PmsSkuStockMapper, PmsSkuStock> implements PmsSkuStockService {

    @Override
    public List<PmsSkuStock> getList(Long pid, String keyword) {
        QueryWrapper<PmsSkuStock> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<PmsSkuStock> lambda = wrapper.lambda();
        lambda.and(w -> w.eq(PmsSkuStock::getProductId,pid));
        if(StrUtil.isNotEmpty(keyword)){
            lambda.and(w -> w.like(PmsSkuStock::getSkuCode,keyword));
        }

        return getBaseMapper().selectList(wrapper);
    }

    @Override
    public int update(Long pid, List<PmsSkuStock> skuStockList) {
        List<PmsSkuStock> filterSkuList = skuStockList.stream()
                .filter(item -> pid.equals(item.getProductId()))
                .collect(Collectors.toList());
        return baseMapper.replaceList(filterSkuList);
    }
}
