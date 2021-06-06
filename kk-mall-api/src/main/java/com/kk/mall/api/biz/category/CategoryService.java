package com.kk.mall.api.biz.category;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenwenkun
 */
@Service
public class CategoryService extends ServiceImpl<CategoryMapper, Category> implements IService<Category> {

    public List<CategoryFrontendListRepresentation> getCategoryList() {
        LambdaQueryWrapper<Category> categoryFrontendLambdaQueryWrapper = new LambdaQueryWrapper<>();
        categoryFrontendLambdaQueryWrapper.orderByDesc(Category::getSort);
        List<Category> categoryFrontends = baseMapper.selectList(categoryFrontendLambdaQueryWrapper);

        return CategoryFrontendListRepresentation.ofList(categoryFrontends);
    }
}
