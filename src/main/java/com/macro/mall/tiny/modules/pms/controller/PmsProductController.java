package com.macro.mall.tiny.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.common.api.CommonPage;
import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.modules.pms.dto.PmsProductParam;
import com.macro.mall.tiny.modules.pms.dto.PmsProductQueryParam;
import com.macro.mall.tiny.modules.pms.model.PmsProduct;
import com.macro.mall.tiny.modules.pms.service.PmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 查询商品
     * @param productQueryParam
     * @param pageSize
     * @param pageNum
     * @return {@link CommonResult }<{@link CommonPage }<{@link PmsProduct }>>
     */
    @GetMapping("/list")
    @ApiOperation("查询商品")
    public CommonResult<CommonPage<PmsProduct>> list(PmsProductQueryParam productQueryParam,
                                                     @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        Page<PmsProduct> page = productService.list(productQueryParam,pageNum,pageSize);

        return CommonResult.success(null);
    }
}

