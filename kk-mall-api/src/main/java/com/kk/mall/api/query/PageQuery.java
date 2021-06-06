package com.kk.mall.api.query;

import lombok.Getter;


@Getter
public class PageQuery {
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
