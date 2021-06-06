package com.kk.mall.api.biz.item.branch;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author  chenwenkun
 */
@Service
public class ItemBranchService extends ServiceImpl<ItemBranchMapper, ItemBranch> implements IService<ItemBranch> {
    public List<BranchMiniLogoVo> getBranchList(List<Long> itemProductIdList) {
        return baseMapper.getBranchList(itemProductIdList);
    }
}
