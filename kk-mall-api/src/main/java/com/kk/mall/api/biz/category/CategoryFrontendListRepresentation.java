package com.kk.mall.api.biz.category;

import com.kk.mall.api.biz.category.frontend.CategoryFrontend;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chenwenkun
 */
@Getter
public class CategoryFrontendListRepresentation {
    private String categoryName;
    private String categoryImgUrl;

    public static List<CategoryFrontendListRepresentation> ofList(List<CategoryFrontend> categoryFrontends) {
       return categoryFrontends.stream().map(categoryFrontend -> {
               CategoryFrontendListRepresentation categoryFrontendListRepresentation=new CategoryFrontendListRepresentation();
               categoryFrontendListRepresentation.categoryName=categoryFrontend.getName();
               categoryFrontendListRepresentation.categoryImgUrl=categoryFrontend.getImageUrl();
               return categoryFrontendListRepresentation;
       }).collect(Collectors.toList());
    }
}
