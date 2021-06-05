package com.kk.mall.api.biz.category.frontend;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.mall.api.biz.category.CategoryFrontendListRepresentation;
import com.kk.mall.api.biz.sys.dict.DictVo;
import com.kk.mall.api.biz.sys.dict.SysDictService;
import com.kk.mall.api.consts.DictKeyConst;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author  chenwenkun
 */
@Service
public class CategoryFrontendService extends ServiceImpl<CategoryFrontendMapper, CategoryFrontend> implements IService<CategoryFrontend> {

    @Resource
    private SysDictService sysDictService;
    public List<CategoryFrontendListRepresentation> getCategoryList() {
        DictVo isSingleCategoryDict = sysDictService.getDict(DictKeyConst.IS_SINGLE_CATEGORY);
        if (isSingleCategoryDict.booleanValue()){
            DictVo singleCategoryDict = sysDictService.getDict(DictKeyConst.SINGLE_CATEGORY);
            LambdaQueryWrapper<CategoryFrontend> categoryFrontendLambdaQueryWrapper=new LambdaQueryWrapper<>();
            categoryFrontendLambdaQueryWrapper.eq(CategoryFrontend::getParentId,singleCategoryDict.intValue());
            categoryFrontendLambdaQueryWrapper.orderByDesc(CategoryFrontend::getSort);
            List<CategoryFrontend> categoryFrontends = baseMapper.selectList(categoryFrontendLambdaQueryWrapper);

            return CategoryFrontendListRepresentation.ofList(categoryFrontends);
        }
        //todo 多品类暂时放置
        return null;
    }
}
