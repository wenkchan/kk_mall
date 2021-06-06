package com.kk.mall.api.biz.item.branch;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenwenkun
 */
@Mapper
public interface ItemBranchMapper extends BaseMapper<ItemBranch> {

    List<BranchMiniLogoVo> getBranchList(@Param("itemProductIdList") List<Long> itemProductIdList);
}
