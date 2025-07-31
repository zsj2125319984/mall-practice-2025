package com.macro.mall.tiny.modules.pms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.common.api.CommonPage;
import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.modules.pms.dto.PmsProductParam;
import com.macro.mall.tiny.modules.pms.dto.PmsProductQueryParam;
import com.macro.mall.tiny.modules.pms.dto.PmsProductResult;
import com.macro.mall.tiny.modules.pms.model.PmsProduct;
import com.macro.mall.tiny.modules.pms.service.PmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        return CommonResult.success(CommonPage.restPage(page));
    }

    /**
     * 根据商品名称或货号模糊查询
     * @param keyword
     * @return {@link CommonResult }<{@link List }<{@link PmsProduct }>>
     */
    @GetMapping("/simpleList")
    @ApiOperation("根据商品名称或货号模糊查询")
    public CommonResult<List<PmsProduct>> simpleList(String keyword){
        List<PmsProduct> productList = productService.list(keyword);

        return CommonResult.success(productList);
    }

    /**
     * 批量设为新品
     * @param ids
     * @param newStatus
     * @return {@link CommonResult }
     */
    @PostMapping("/update/newStatus")
    @ApiOperation("批量设为新品")
    public CommonResult updateNewStatus(@RequestParam("ids") List<Long> ids,
                                        @RequestParam("newStatus") Integer newStatus){
        int count = productService.updateNewStatus(ids,newStatus);

        if(count > 0){
            return CommonResult.success(count);
        }

        return CommonResult.failed();
    }

    /**
     * 批量修改删除状态
     * @param ids
     * @param deleteStatus
     * @return {@link CommonResult }
     */
    @PostMapping("/update/deleteStatus")
    @ApiOperation("批量修改删除状态")
    public CommonResult updateDeleteStatus(@RequestParam("ids") List<Long> ids,
                                           @RequestParam("deleteStatus") Integer deleteStatus){
        int count = productService.updateDeleteStatus(ids,deleteStatus);

        if(count > 0){
            return CommonResult.success(count);
        }

        return CommonResult.failed();
    }

    /**
     * 批量上下架商品
     * @param ids
     * @param publishStatus
     * @return {@link CommonResult }
     */
    @PostMapping("/update/publishStatus")
    @ApiOperation("批量上下架商品")
    public CommonResult updatePublishStatus(@RequestParam("ids") List<Long> ids,
                                           @RequestParam("publishStatus") Integer publishStatus){
        int count = productService.updatePublishStatus(ids,publishStatus);

        if(count > 0){
            return CommonResult.success(count);
        }

        return CommonResult.failed();
    }

    /**
     * 批量推荐商品
     * @param ids
     * @param recommendStatus
     * @return {@link CommonResult }
     */
    @PostMapping("/update/recommendStatus")
    @ApiOperation("批量推荐商品")
    public CommonResult updateRecommendStatus(@RequestParam("ids") List<Long> ids,
                                            @RequestParam("recommendStatus") Integer recommendStatus){
        int count = productService.updateRecommendStatus(ids,recommendStatus);

        if(count > 0){
            return CommonResult.success(count);
        }

        return CommonResult.failed();
    }

    /**
     * 根据商品id获取商品编辑信息
     * @param id
     * @return {@link CommonResult }<{@link PmsProductResult }>
     */
    @GetMapping("/updateInfo/{id}")
    @ApiOperation("根据商品id获取商品编辑信息")
    public CommonResult<PmsProductResult> updateInfo(@PathVariable Long id){
        PmsProductResult productResult = productService.updateInfo(id);

        return CommonResult.success(productResult);
    }

    @PostMapping("/update/verifyStatus")
    @ApiOperation("批量修改审核状态")
    public CommonResult updateVerifyStatus(@RequestParam("detail") String detail,
                                           @RequestParam("ids") List<Long> ids,
                                           @RequestParam("verifyStatus") Integer verifyStatus){
        int count = productService.updateVerifyStatus(detail,ids,verifyStatus);

        if(count > 0){
            return CommonResult.success(count);
        }

        return CommonResult.failed();
    }

    /**
     * 更新商品
     * @param id
     * @param productParam
     * @return {@link CommonResult }
     */
    @PostMapping("/update/{id}")
    @ApiOperation("更新商品")
    public CommonResult update(@PathVariable Long id,
                               @RequestBody PmsProductParam productParam){
        int count = productService.update(id,productParam);

        if(count > 0){
            return CommonResult.success(count);
        }

        return CommonResult.failed();
    }
}

