package com.macro.mall.tiny.modules.pms.controller;


import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.modules.pms.dto.PmsProductParam;
import com.macro.mall.tiny.modules.pms.service.PmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商品信息 前端控制器
 * </p>
 *
 * @author Sicecream
 * @since 2025-07-28
 */
@RestController
@RequestMapping("/product")
@Api(tags = "PmsProductController")
@Tag(name = "PmsProductController",description = "商品管理")
public class PmsProductController {

    @Autowired
    private PmsProductService productService;

    /**
     * 创建商品
     * @param pmsProductParam
     * @return {@link CommonResult }
     */
    @PostMapping("/create")
    @ApiOperation(("创建商品"))
    public CommonResult create(@RequestBody PmsProductParam pmsProductParam){
        int count = productService.create(pmsProductParam);

        if(count > 0){
            return CommonResult.success(count);
        }

        return CommonResult.failed();
    }
}

