package com.macro.mall.tiny.modules.oms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.modules.oms.dto.OmsOrderReturnApplyResult;
import com.macro.mall.tiny.modules.oms.dto.OmsReturnApplyQueryParam;
import com.macro.mall.tiny.modules.oms.dto.OmsUpdateStatusParam;
import com.macro.mall.tiny.modules.oms.model.OmsOrderReturnApply;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单退货申请 服务类
 * </p>
 *
 * @author Sicecream
 * @since 2025-08-02
 */
public interface OmsOrderReturnApplyService extends IService<OmsOrderReturnApply> {
    /**
     * 分页查询申请
     */
    Page<OmsOrderReturnApply> list(OmsReturnApplyQueryParam queryParam, Integer pageSize, Integer pageNum);

    /**
     * 批量删除申请
     */
    int delete(List<Long> ids);

    /**
     * 修改指定申请状态
     */
    int updateStatus(Long id, OmsUpdateStatusParam statusParam);

    /**
     * 获取指定申请详情
     */
    OmsOrderReturnApplyResult getItem(Long id);
}
