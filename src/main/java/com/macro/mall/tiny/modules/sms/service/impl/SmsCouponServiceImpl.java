package com.macro.mall.tiny.modules.sms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.sms.dto.SmsCouponParam;
import com.macro.mall.tiny.modules.sms.model.SmsCoupon;
import com.macro.mall.tiny.modules.sms.mapper.SmsCouponMapper;
import com.macro.mall.tiny.modules.sms.model.SmsCouponProductCategoryRelation;
import com.macro.mall.tiny.modules.sms.model.SmsCouponProductRelation;
import com.macro.mall.tiny.modules.sms.service.SmsCouponProductCategoryRelationService;
import com.macro.mall.tiny.modules.sms.service.SmsCouponProductRelationService;
import com.macro.mall.tiny.modules.sms.service.SmsCouponService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 优惠券表 服务实现类
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-04
 */
@Service
public class SmsCouponServiceImpl extends ServiceImpl<SmsCouponMapper, SmsCoupon> implements SmsCouponService {

    @Autowired
    private SmsCouponProductRelationService couponProductRelationService;
    @Autowired
    private SmsCouponProductCategoryRelationService couponProductCategoryRelationService;

    @Override
    public int create(SmsCouponParam couponParam) {
        couponParam.setCount(couponParam.getPublishCount());
        couponParam.setUseCount(0);
        couponParam.setReceiveCount(0);
        //插入优惠券表
        boolean success = save(couponParam);
        //插入优惠券和商品关系表
        if(couponParam.getUseType().equals(2)){
            for(SmsCouponProductRelation productRelation:couponParam.getProductRelationList()){
                productRelation.setCouponId(couponParam.getId());
            }
            couponProductRelationService.saveBatch(couponParam.getProductRelationList());
        }
        //插入优惠券和商品分类关系表
        if(couponParam.getUseType().equals(1)){
            for (SmsCouponProductCategoryRelation couponProductCategoryRelation : couponParam.getProductCategoryRelationList()) {
                couponProductCategoryRelation.setCouponId(couponParam.getId());
            }
            couponProductCategoryRelationService.saveBatch(couponParam.getProductCategoryRelationList());
        }
        return success ? 1 : 0;
    }

    @Override
    public int delete(Long id) {
        //删除优惠券
        boolean success = removeById(id);
        //删除商品关联
        deleteProductRelation(id);
        //删除商品分类关联
        deleteProductCategoryRelation(id);
        return success ? 1 : 0;
    }

    private void deleteProductCategoryRelation(Long id) {
        QueryWrapper<SmsCouponProductCategoryRelation> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SmsCouponProductCategoryRelation::getCouponId,id);

        couponProductCategoryRelationService.remove(wrapper);
    }

    private void deleteProductRelation(Long id) {
        QueryWrapper<SmsCouponProductRelation> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SmsCouponProductRelation::getCouponId,id);

        couponProductRelationService.remove(wrapper);
    }

    @Override
    public int update(Long id, SmsCouponParam couponParam) {
        couponParam.setId(id);
        boolean success = updateById(couponParam);
        //删除后插入优惠券和商品关系表
        if(couponParam.getUseType().equals(2)){
            for(SmsCouponProductRelation productRelation:couponParam.getProductRelationList()){
                productRelation.setCouponId(couponParam.getId());
            }
            deleteProductRelation(id);
            couponProductRelationService.saveBatch(couponParam.getProductRelationList());
        }
        //删除后插入优惠券和商品分类关系表
        if(couponParam.getUseType().equals(1)){
            for (SmsCouponProductCategoryRelation couponProductCategoryRelation : couponParam.getProductCategoryRelationList()) {
                couponProductCategoryRelation.setCouponId(couponParam.getId());
            }
            deleteProductCategoryRelation(id);
            couponProductCategoryRelationService.saveBatch(couponParam.getProductCategoryRelationList());
        }
        return success ? 1 : 0;
    }

    @Override
    public Page<SmsCoupon> list(String name, Integer type, Integer pageSize, Integer pageNum) {
        Page<SmsCoupon> page = new Page<>(pageNum,pageSize);
        QueryWrapper<SmsCoupon> wrapper = new QueryWrapper<>();
        if(StrUtil.isNotEmpty(name)){
            wrapper.lambda().and(w ->
                    w.like(SmsCoupon::getName,name));
        }
        if(type != null){
            wrapper.lambda().and(w ->
                    w.eq(SmsCoupon::getType,type));
        }

        return page(page,wrapper);
    }

    @Override
    public SmsCouponParam getItem(Long id) {
        return baseMapper.getItem(id);
    }
}
