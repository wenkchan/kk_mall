package com.kk.mall.api.biz.item.set.branch;

import com.kk.mall.api.consts.SwaggerGroup;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Wenk.Chan
 */
@RestController
@AllArgsConstructor
@RequestMapping("/item/set/branch")
@Api(tags = SwaggerGroup.BRANCH)
public class ItemSetBranchController {
    @Resource
    public ItemSetBranchService itemSetBranchService;

    @ApiOperation("套组品牌列表")
    @GetMapping("/list")
    public List<BranchSmallLogoResp> getItemSetBranchList(){
       return itemSetBranchService.getItemSetBranchList();
    }
}
