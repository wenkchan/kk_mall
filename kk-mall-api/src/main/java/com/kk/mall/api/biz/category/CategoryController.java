package com.kk.mall.api.biz.category;


import com.kk.mall.api.biz.category.frontend.CategoryFrontendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenwenkun
 */
@RestController
@RequestMapping("category")
@Api(tags = "分类相关")
public class CategoryController {

    @Resource
    private CategoryFrontendService categoryFrontendService;

    @ApiOperation("获取前端分类列表")
    @GetMapping("/frontend/list")
    public List<CategoryFrontendListRepresentation> getFrontendCategoryList() {
        return categoryFrontendService.getCategoryList();
    }
}
