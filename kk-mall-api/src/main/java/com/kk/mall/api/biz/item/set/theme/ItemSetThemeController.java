package com.kk.mall.api.biz.item.set.theme;

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
@RequestMapping("/item/set/theme")
@Api(tags = SwaggerGroup.THEME)
public class ItemSetThemeController {

    @Resource
    public  ItemSetThemeService itemSetThemeService;
    @ApiOperation("套组主题列表")
    @GetMapping("list")
    public List<ItemSetThemeResp> getItemSetThemeList(){
        return itemSetThemeService.getItemSetThemeList();
    }


}
