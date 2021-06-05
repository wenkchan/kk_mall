package com.kk.mall.api.biz.sys.dict;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.mall.api.biz.sys.dict.item.SysDictItem;
import com.kk.mall.api.biz.sys.dict.item.SysDictItemService;
import com.kk.mall.api.enums.ValidEnum;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenwenkun
 */
@Service
public class SysDictService extends ServiceImpl<SysDictMapper, SysDict> implements IService<SysDict> {

    @Resource
    private SysDictItemService sysDictItemService;


    public DictVo getDict(String key, String subKey) {
        return getDictList(key).stream().filter(dictVo -> dictVo.getSubKey().equals(subKey)).findFirst().orElse(null);
    }

    public DictVo getDict(String key) {
        return getDictList(key).stream().findFirst().orElse(null);
    }

    public List<DictVo> getDictList(String key) {
        LambdaQueryWrapper<SysDict> dictLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dictLambdaQueryWrapper.eq(SysDict::getDictKey, key);
        dictLambdaQueryWrapper.eq(SysDict::getValid, ValidEnum.VALID.getValue());
        SysDict sysDict = this.baseMapper.selectOne(dictLambdaQueryWrapper);

        if (sysDict == null) {
            return new ArrayList<>();
        }

        LambdaQueryWrapper<SysDictItem> sysDictItemLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sysDictItemLambdaQueryWrapper.eq(SysDictItem::getValid, ValidEnum.VALID.getValue());
        sysDictItemLambdaQueryWrapper.orderByDesc(SysDictItem::getSort);
        sysDictItemLambdaQueryWrapper.eq(SysDictItem::getSysDictId, sysDict.getId());
        List<SysDictItem> sysDictItems = sysDictItemService.list(sysDictItemLambdaQueryWrapper);
        if (null != sysDictItems && !sysDictItems.isEmpty()) {
            return DictVo.of(sysDict, sysDictItems);
        }
        return new ArrayList<>();
    }

}
