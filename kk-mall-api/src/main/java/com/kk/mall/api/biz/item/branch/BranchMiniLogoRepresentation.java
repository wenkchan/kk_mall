package com.kk.mall.api.biz.item.branch;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author chenwenkun
 */
@Getter
@AllArgsConstructor
public class BranchMiniLogoRepresentation {
    private final String productCode;
    private final String branchName;
    private final String logoMiniSizeUrl;
}
