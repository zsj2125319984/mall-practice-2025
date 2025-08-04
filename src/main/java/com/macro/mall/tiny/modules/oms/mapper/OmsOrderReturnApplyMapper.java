package com.macro.mall.tiny.modules.oms.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.macro.mall.tiny.modules.oms.dto.OmsOrderReturnApplyResult;
import com.macro.mall.tiny.modules.oms.dto.OmsReturnApplyQueryParam;
import com.macro.mall.tiny.modules.oms.model.OmsOrderReturnApply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单退货申请 Mapper 接口
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-02
 */
public interface OmsOrderReturnApplyMapper extends BaseMapper<OmsOrderReturnApply> {
    /**
     * 查询申请列表
     */
    IPage<OmsOrderReturnApply> getList(IPage<OmsOrderReturnApply> page,@Param("queryParam") OmsReturnApplyQueryParam queryParam);

    /**
     * 获取申请详情
     */
    OmsOrderReturnApplyResult getDetail(@Param("id")Long id);
}
