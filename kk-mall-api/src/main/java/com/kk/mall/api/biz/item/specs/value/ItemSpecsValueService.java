package com.kk.mall.api.biz.item.specs.value;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kk.mall.api.biz.item.specs.SpecsVo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author  chenwenkun
 */
@Service
public class ItemSpecsValueService extends ServiceImpl<ItemSpecsValueMapper, ItemSpecsValue> implements IService<ItemSpecsValue> {

    public List<SpecsVo> getSpecsList(List<Long> itemProductIdList) {
        return baseMapper.getSpecsList(itemProductIdList);
    }
}
