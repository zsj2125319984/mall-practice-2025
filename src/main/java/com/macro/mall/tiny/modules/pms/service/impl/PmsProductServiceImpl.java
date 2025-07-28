package com.macro.mall.tiny.modules.pms.service.impl;

import com.macro.mall.tiny.modules.pms.dto.PmsProductParam;
import com.macro.mall.tiny.modules.pms.model.PmsProduct;
import com.macro.mall.tiny.modules.pms.mapper.PmsProductMapper;
import com.macro.mall.tiny.modules.pms.service.PmsProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品信息 服务实现类
 * </p>
 *
 * @author macro
 * @since 2025-07-28
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements PmsProductService {
    /**
     * 创建商品
     * @param pmsProductParam
     * @return int
     */
    @Override
    public int create(PmsProductParam pmsProductParam) {
        //获取商品实体类
        //插入数据库
        //关联会员价格
        //关联阶梯价格
        //关联满减价格
        //处理sku编码
        //关联sku库存
        //关联商品参数
        //关联专题
        //关联优选
        //返回结果
        return 0;
    }
}
